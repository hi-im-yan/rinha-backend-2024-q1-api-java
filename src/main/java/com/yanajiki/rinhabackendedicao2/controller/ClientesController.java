package com.yanajiki.rinhabackendedicao2.controller;

import com.yanajiki.rinhabackendedicao2.controller.form.TransacaoForm;
import com.yanajiki.rinhabackendedicao2.controller.response.Extrato;
import com.yanajiki.rinhabackendedicao2.controller.response.ResultadoTransacao;
import com.yanajiki.rinhabackendedicao2.exception.ClienteNaoEncontradoException;
import com.yanajiki.rinhabackendedicao2.exception.TransacaoNaoProcessavelException;
import com.yanajiki.rinhabackendedicao2.model.Cliente;
import com.yanajiki.rinhabackendedicao2.model.ClienteJpaRepository;
import com.yanajiki.rinhabackendedicao2.model.Transacao;
import com.yanajiki.rinhabackendedicao2.model.TransacaoJpaRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClientesController {

    private final ClienteJpaRepository clienteRepository;
    private final TransacaoJpaRepository transacaoRepository;

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<ResultadoTransacao> registraTransacao(@PathVariable("id") Long id, @RequestBody @Valid TransacaoForm transacaoForm) {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);

        Transacao transacao = new Transacao(transacaoForm);
        cliente.processaTransacao(transacao);

        this.transacaoRepository.save(transacao);
        this.clienteRepository.save(cliente);

        return ResponseEntity.status(200).body(new ResultadoTransacao(cliente));
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<Extrato> geraExtrato(@PathVariable("id") Long id) {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);

        Extrato extrato = new Extrato(cliente);
        return ResponseEntity.status(200).body(extrato);
    }

    @ExceptionHandler
    public ResponseEntity<Void> clienteNaoEncontradoHandler(ClienteNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> TransacaoNoaProcessavelHandler(TransacaoNaoProcessavelException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = bindingResult.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
}
