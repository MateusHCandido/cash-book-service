package com.mtzz.services;


import com.mtzz.entities.Cliente;
import com.mtzz.entities.LivroCaixa;
import com.mtzz.repositories.ClienteRepository;
import com.mtzz.repositories.LivroCaixaRepository;
import com.mtzz.services.exceptions.RegisterNotFoundException;
import com.mtzz.services.exceptions.ValueNotFoundException;
import com.mtzz.services.util.FormatData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@Service
public class LivroCaixaService extends FormatData {

    @Autowired
    private LivroCaixaRepository livroCaixa;

    @Autowired
    private ClienteRepository clienteRepository;

    public LivroCaixa create(LivroCaixa lc){
        try {
            lc.setDescricao(nameFormat(lc.getDescricao()));
            lc.setDataLancamento(dateFormat());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return livroCaixa.save(lc);
    }

    public Optional<LivroCaixa> findById(Long id){
        Optional<LivroCaixa> lc = livroCaixa.findById(id);
        if(lc == null) {
            throw new RegisterNotFoundException("Register not found");
        }
        return  lc;
    }

    public LivroCaixa update(LivroCaixa lc){
        LivroCaixa newLC = livroCaixa.getReferenceById(lc.getId());
        newLC = updateData(newLC, lc);
         return livroCaixa.save(newLC);
    }

    public void delete(Long id){
        try {
            LivroCaixa lc = livroCaixa.getReferenceById(id);
        }catch (NullPointerException err){
            throw new RegisterNotFoundException("Register not found");
        }
        livroCaixa.deleteById(id);
    }

    public List<LivroCaixa> findByCliente(Long cliente_id){
        return livroCaixa.findByCliente_id(cliente_id);
    }

    protected LivroCaixa updateData(LivroCaixa livroCaixa, LivroCaixa lc){
        try{
            livroCaixa.setDescricao(nameFormat(lc.getDescricao()));
            livroCaixa.setTipo(nameFormat(lc.getTipo()));
            livroCaixa.setValor(lc.getValor());
        }catch (NullPointerException err) {
            throw new ValueNotFoundException("Change value referring to description, type and value.");
        }
        return livroCaixa;
    }


}
