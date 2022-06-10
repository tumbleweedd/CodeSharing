package com.example.codesharing.service;

import com.example.codesharing.model.Code;
import com.example.codesharing.repository.CodeSharingReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeSharingServiceImpl implements CodeSharingService {

    private final CodeSharingReposiroty codeSharingReposiroty;

    @Autowired
    public CodeSharingServiceImpl(CodeSharingReposiroty codeSharingReposiroty) {
        this.codeSharingReposiroty = codeSharingReposiroty;
    }

    @Override
    public Code findCodeById(Integer id) {
        return codeSharingReposiroty.findCodeById(id);
    }


    @Override
    public Code save(Code toSave) {
        return codeSharingReposiroty.save(toSave);
    }

    @Override
    public List<Code> readAll() {
        return (List<Code>) codeSharingReposiroty.findAll();
    }

    @Override
    public boolean update(Code code, int id) {
        if (codeSharingReposiroty.existsById((long) id)) {
            code.setId(id);
            codeSharingReposiroty.save(code);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (codeSharingReposiroty.existsById(id)) {
            codeSharingReposiroty.deleteById(id);
            return true;
        }
        return false;
    }


}
