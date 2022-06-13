package com.example.codesharing.service;

import com.example.codesharing.model.Code;
import com.example.codesharing.repository.CodeSharingReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class CodeSharingServiceImpl implements CodeSharingService {

    private final CodeSharingReposiroty codeSharingReposiroty;

    @Autowired
    public CodeSharingServiceImpl(CodeSharingReposiroty codeSharingReposiroty) {
        this.codeSharingReposiroty = codeSharingReposiroty;
    }

    @Override
    public Code findCodeById(String id) {
        return codeSharingReposiroty.findCodeById(id);
    }


    @Override
    public String save(Code toSave) {
        UUID uuid = UUID.randomUUID();
        Code newCode = new Code(toSave.getCode(), toSave.getTime(), toSave.getViews(), uuid.toString());
        codeSharingReposiroty.save(newCode);
        return newCode.getId();
    }

    @Override
    public List<Code> readAll() {
        return (List<Code>) codeSharingReposiroty.findAll();
    }

    @Override
    public boolean update(Code code, String id) {
        if (codeSharingReposiroty.existsById(Long.valueOf(id))) {
            code.setId(id);
            codeSharingReposiroty.save(code);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Code code) {
        codeSharingReposiroty.delete(code);
    }

    @Override
    public boolean checkExpired(Code code, LocalDateTime currentTime) {
        boolean expired = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(code.getDate(), formatter);

        if (code.isTimeRestricted()) {
            if (Duration.between(dateTime, currentTime).toSeconds() >= code.getTime()) {
                expired = true;
            } else {
                code.setTime((int) (code.getTime() - Duration.between(dateTime, currentTime).toSeconds()));
                codeSharingReposiroty.save(code);
                if (code.getTime() <= 0) {
                    code.setTime(0);
                    expired = true;
                }
            }
        }

        if (code.isViewsRestricted()) {
            if (code.getViews() <= 0) {
                expired = true;
            } else {
                code.setViews(code.getViews() - 1);
                codeSharingReposiroty.save(code);
                if (code.getViews() <= 0) {
                    expired = true;
                }
            }
        }
        return expired;
    }
}
