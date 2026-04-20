package demo_hackathon.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo_hackathon.example.demo.Dto.CreditResponseDTO;
import demo_hackathon.example.demo.model.CarbonCredit;
import demo_hackathon.example.demo.repository.CarbonCreditRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CreditService {

    private final CarbonCreditRepository creditRepo;

    public List<CreditResponseDTO> getAll() {
        return creditRepo.findAll().stream()
                .map(c -> new CreditResponseDTO(
                        c.getId(),
                        c.getCarbonProject().getId(),
                        c.getTotalCredits(),
                        c.getAvailableCredits(),
                        c.getCreatedAt(),
                        c.getIssueDate(),
                        c.getCreditStatus()
                )).toList();
    }

    public CreditResponseDTO getByProject(Long projectId) {
        CarbonCredit c = creditRepo.findByCarbonProject_Id(projectId);

        return new CreditResponseDTO(
                c.getId(),
                c.getCarbonProject().getId(),
                c.getTotalCredits(),
                c.getAvailableCredits(),
                c.getCreatedAt(),
                c.getIssueDate(),
                c.getCreditStatus()
        );
    }
}