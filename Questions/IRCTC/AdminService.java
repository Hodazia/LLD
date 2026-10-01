package IRCTC;
import IRCTC.enums.*;
    
public class AdminService {
    private final TrainRepository trainRepository;
    private final JourneyRepository journeyRepository;

    AdminService(TrainRepository trainRepository,
                 JourneyRepository journeyRepository) {
        this.trainRepository = trainRepository;
        this.journeyRepository = journeyRepository;
    }

    public void addTrain(Admin admin, Train train) {
        requireAdmin(admin);
        trainRepository.save(train);
    }

    public void cancelJourney(
            Admin admin, String journeyId) {
        requireAdmin(admin);

        TrainJourney journey = journeyRepository
                .findById(journeyId)
                .orElseThrow(() ->
                        new RuntimeException("Journey not found"));

        journey.status = JourneyStatus.CANCELLED;
    }

    private void requireAdmin(User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new SecurityException("Admin only");
        }
    }
}

