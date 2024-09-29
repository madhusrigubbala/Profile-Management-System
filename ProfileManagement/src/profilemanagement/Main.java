package profilemanagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String loginUsername = null;

	public static void main(String[] args) throws SQLException {
        Authentication auth = new Authentication();
        ProfileManager profileManager = new ProfileManager();
        SearchService searchService = new SearchService(profileManager);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("*****ProfileManagement*****");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Enter username:");
                    String regUsername = scanner.nextLine();
                    System.out.println("Enter password:");
                    String regPassword = scanner.nextLine();
                    System.out.println("Enter email:");
                    String regEmail = scanner.nextLine();
                    System.out.println("Enter contact:");
                    String regContact = scanner.nextLine();

                    if (auth.registerUser(regUsername, regPassword, regEmail, regContact)) {
                        try {
                            profileManager.createProfile(regUsername);
                            ActivityLogger.log(regUsername, "Registered");
                            System.out.println("Registration successful!");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Username already exists. Try again.");
                    }
                    break;
                case 2:
                    System.out.println("Enter username:");
                    String logUsername = scanner.nextLine();
                    System.out.println("Enter password:");
                    String logPassword = scanner.nextLine();

                    User user = auth.loginUser(logUsername, logPassword);
                    if (user != null) {
                        ActivityLogger.log(user.getUsername(), "Logged in");
                        System.out.println("Login successful!");
                        boolean loggedIn = true;
                        while (loggedIn) {
                            System.out.println("1. View Profile");
                            System.out.println("2. Edit Profile");
                            System.out.println("3. Upload Profile Picture");
                            System.out.println("4. Remove Profile Picture");
                            System.out.println("5. Search Profiles");
                            System.out.println("6. Logout");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine(); 
                            switch (userChoice) {
                            case 1:
                                Profile profile = profileManager.getProfile(loginUsername);
                                if (profile != null) {
                                    System.out.println("Profile Details:");
                                    System.out.println("Personal Information: " + profile.getPersonalInformation());
                                    System.out.println("Skills: " + profile.getSkills());
                                    System.out.println("Location: " + profile.getLocation());
                                    System.out.println("Interests: " + profile.getInterests());
                                } else {
                                    System.out.println("Profile not found.");
                                }
                                    break;
                                case 2:
                                    Profile existingProfile = profileManager.getProfile(user.getUsername());
                                    if (existingProfile == null) {
                                        existingProfile = new Profile();
                                    }
                                    System.out.println("Enter personal information:");
                                    existingProfile.setPersonalInformation(scanner.nextLine());
                                    System.out.println("Enter education:");
                                    existingProfile.setEducation(scanner.nextLine());
                                    System.out.println("Enter work experience:");
                                    existingProfile.setWorkExperience(scanner.nextLine());
                                    System.out.println("Enter skills (comma-separated):");
                                    existingProfile.setSkills(List.of(scanner.nextLine().split(",")));
                                    System.out.println("Enter interests (comma-separated):");
                                    existingProfile.setInterests(List.of(scanner.nextLine().split(",")));
                                    profileManager.updateProfile(user.getUsername(), existingProfile);
                                    ActivityLogger.log(user.getUsername(), "Updated profile");
                                    System.out.println("Profile updated successfully!");
                                    break;
                                case 3:
                                    Profile profileWithPic = profileManager.getProfile(user.getUsername());
                                    if (profileWithPic != null) {
                                        System.out.println("Enter profile picture URL:");
                                        profileWithPic.setProfilePicture(scanner.nextLine());
                                        profileManager.updateProfile(user.getUsername(), profileWithPic);
                                        ActivityLogger.log(user.getUsername(), "Uploaded profile picture");
                                        System.out.println("Profile picture uploaded successfully!");
                                    } else {
                                        System.out.println("Profile not found.");
                                    }
                                    break;
                                case 4:
                                    Profile profileWithoutPic = profileManager.getProfile(user.getUsername());
                                    if (profileWithoutPic != null) {
                                        profileWithoutPic.removeProfilePicture();
                                        profileManager.updateProfile(user.getUsername(), profileWithoutPic);
                                        ActivityLogger.log(user.getUsername(), "Removed profile picture");
                                        System.out.println("Profile picture removed successfully!");
                                    } else {
                                        System.out.println("Profile not found.");
                                    }
                                    break;
                                case 5:
                                    System.out.println("Enter name to search (or leave blank):");
                                    String name = scanner.nextLine();
                                    System.out.println("Enter skill to search (or leave blank):");
                                    String skill = scanner.nextLine();
                                    System.out.println("Enter location to filter (or leave blank):");
                                    String location = scanner.nextLine();
                                    System.out.println("Enter interest to search (or leave blank):");
                                    String interest = scanner.nextLine();

                                    List<Profile> searchResults = searchService.searchProfiles(name, skill, location, interest);
                                    if (searchResults.isEmpty()) {
                                        System.out.println("No profiles found.");
                                    } else {
                                        for (Profile profileResult : searchResults) {
                                            System.out.println(profileResult);
                                        }
                                    }
                                    break;
                                case 6:
                                    ActivityLogger.log(user.getUsername(), "Logged out");
                                    loggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again.");
                            }
                        }
                    } else {
                        System.out.println("Invalid username or password. Try again.");
                    }
                    break;
                case 3:
                    System.out.println("****Exiting...****");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
