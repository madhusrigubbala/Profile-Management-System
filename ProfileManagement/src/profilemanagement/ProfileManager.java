package profilemanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileManager {
    private Connection connection;
    private List<Profile> allProfiles;

    public ProfileManager() throws SQLException {
        this.connection = DBConnection.getConnection();
        this.allProfiles = new ArrayList<>();
        loadProfilesFromDatabase();
    }

    private void loadProfilesFromDatabase() {
        String query = "SELECT * FROM profiles";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Profile profile = new Profile();
                profile.setUsername(rs.getString("username"));
                profile.setPersonalInformation(rs.getString("personal_information"));
                profile.setEducation(rs.getString("education"));
                profile.setWorkExperience(rs.getString("work_experience"));
                profile.setSkills(List.of(rs.getString("skills").split(",")));
                profile.setInterests(List.of(rs.getString("interests").split(",")));
                profile.setProfilePicture(rs.getString("profile_picture"));
                profile.setLocation(rs.getString("location"));
                allProfiles.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Profile getProfile(String username) {
        for (Profile profile : allProfiles) {
            if (profile.getUsername().equals(username)) {
                return profile;
            }
        }
        return null;
    }

	private List<String> getSkills(String username) {
		List<String> skills = new ArrayList<>();
		// Query to fetch skills from database and populate the list
		return skills;
	}

	private List<String> getInterests(String username) {
		List<String> interests = new ArrayList<>();
		// Query to fetch interests from database and populate the list
		return interests;
	}

	public void updateProfile(String username, Profile existingProfile) {
		// TODO Auto-generated method stub

	}

	public void createProfile(String regUsername) {
		// TODO Auto-generated method stub

	}

	public void updateProfile1(String loginUsername, String newPersonalInfo, List<String> newSkills, String newLocation,
			List<String> newInterests) {
		// TODO Auto-generated method stub

	}

	public void updateProfile(String loginUsername, String newPersonalInfo, List<String> newSkills, String newLocation,
			List<String> newInterests) {
		// TODO Auto-generated method stub

	}

	

	public List<Profile> getAllProfiles() {
		// TODO Auto-generated method stub
		return null;
	}
}
