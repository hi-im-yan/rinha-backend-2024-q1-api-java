package com.yanajiki.rinhabackendedicao2.controller;

import com.yanajiki.rinhabackendedicao2.controller.form.TransacaoForm;
import com.yanajiki.rinhabackendedicao2.controller.response.ResultadoTransacao;
import com.yanajiki.rinhabackendedicao2.exception.ClienteNaoEncontradoException;
import com.yanajiki.rinhabackendedicao2.exception.TransacaoNaoProcessavelException;
import com.yanajiki.rinhabackendedicao2.model.Cliente;
import com.yanajiki.rinhabackendedicao2.model.ClienteJpaRepository;
import com.yanajiki.rinhabackendedicao2.model.Transacao;
import com.yanajiki.rinhabackendedicao2.model.TransacaoJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClientesController {

    private final ClienteJpaRepository clienteRepository;
    private final TransacaoJpaRepository transacaoRepository;

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<ResultadoTransacao> registraTransacao(@PathVariable("id") Long id, @RequestBody TransacaoForm transacaoForm) {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);

        Transacao transacao = new Transacao(transacaoForm);
        cliente.processaTransacao(transacao);

        this.transacaoRepository.save(transacao);
        this.clienteRepository.save(cliente);

        return ResponseEntity.status(200).body(new ResultadoTransacao(cliente));
    }

    @ExceptionHandler
    public ResponseEntity<Void> clienteNaoEncontradoHandler(ClienteNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> TransacaoNoaProcessavelHandler(TransacaoNaoProcessavelException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
}
