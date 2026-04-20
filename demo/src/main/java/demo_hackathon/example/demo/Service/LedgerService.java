package demo_hackathon.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo_hackathon.example.demo.Dto.LedgerResponseDTO;
import demo_hackathon.example.demo.model.CreditLedger;
import demo_hackathon.example.demo.repository.CreditLedgerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final CreditLedgerRepository ledgerRepo;

    public List<LedgerResponseDTO> getAll() {
        return ledgerRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<LedgerResponseDTO> getByCredit(Long creditId) {
        return ledgerRepo.findByCarbonCredit_Id(creditId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // 🔁 Centralized mapping (so you don’t repeat mistakes everywhere)
    private LedgerResponseDTO mapToDTO(CreditLedger l) {
        return new LedgerResponseDTO(
                l.getId(),
                l.getCarbonCredit().getId(),      // creditId
                l.getTransactionType(),           // type
                l.getQuantity(),                  // quantity
                l.getBalanceAfterTransaction(),   // balance
                l.getTransactionDate()            // timestamp
        );
    }
}