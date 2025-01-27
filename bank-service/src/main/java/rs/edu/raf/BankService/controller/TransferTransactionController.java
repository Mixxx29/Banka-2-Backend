package rs.edu.raf.BankService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.BankService.data.dto.ContractDto;
import rs.edu.raf.BankService.data.dto.ExternalTransferTransactionDto;
import rs.edu.raf.BankService.data.dto.GenericTransactionDto;
import rs.edu.raf.BankService.data.dto.InternalTransferTransactionDto;
import rs.edu.raf.BankService.data.enums.TransactionStatus;
import rs.edu.raf.BankService.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@CrossOrigin
public class TransferTransactionController {

    private final TransactionService transactionService;

    @PostMapping(path = "/internal")
    public InternalTransferTransactionDto createInternalTransferTransaction(
            @RequestBody InternalTransferTransactionDto internalTransferTransactionDto) {
        return transactionService.createInternalTransferTransaction(internalTransferTransactionDto);
    }

    @PostMapping(path = "/external")
    public ExternalTransferTransactionDto createExternalTransferTransaction(
            @RequestBody ExternalTransferTransactionDto externalTransferTransactionDto) {
        return transactionService.createExternalTransferTransaction(externalTransferTransactionDto);
    }

    @PatchMapping(path = "/verify/{transactionId}")
    public TransactionStatus verifyTransaction(@PathVariable Long transactionId,
                                               @RequestParam String verificationToken) {
        return transactionService.verifyTransaction(transactionId, verificationToken);
    }

    @GetMapping(path = "/funds-transfer/{userId}")
    public List<GenericTransactionDto> getAllTransaction(@PathVariable Long userId) {
        return transactionService.getTransferTransactions(userId);
    }

    @GetMapping(path = "/funds-transfer-by-email/{email}")
    public List<GenericTransactionDto> getAllTransactionByEmail(@PathVariable String email) {
        return transactionService.getTransferTransactionsByEmail(email);
    }

    @PostMapping("/securities")

    public ResponseEntity<?> createSecuritiesTransaction(@RequestBody ContractDto contractDto) {

        return ResponseEntity.ok(transactionService.createSecuritiesTransaction(contractDto));
    }

}
