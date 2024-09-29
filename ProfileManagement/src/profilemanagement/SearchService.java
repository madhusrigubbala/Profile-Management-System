package profilemanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private final ProfileManager profileManager;

    public SearchService(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    public List<Profile> searchProfiles(String name, String skill, String location, String interest) {
        List<Profile> allProfiles = profileManager.getAllProfiles();
        return allProfiles.stream()
            .filter(profile -> (name.isEmpty() || (profile.getPersonalInformation() != null && profile.getPersonalInformation().contains(name)))
                && (skill.isEmpty() || (profile.getSkills() != null && profile.getSkills().contains(skill)))
                && (location.isEmpty() || (profile.getLocation() != null && profile.getLocation().contains(location)))
                && (interest.isEmpty() || (profile.getInterests() != null && profile.getInterests().contains(interest))))
            .collect(Collectors.toList());
    }
}
