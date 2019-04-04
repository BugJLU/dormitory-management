package com.jtliu.dormitorymanagement.service;

import com.jtliu.dormitorymanagement.controller.vo.GuestRecordVo;
import com.jtliu.dormitorymanagement.model.GuestRecord;
import com.jtliu.dormitorymanagement.model.User;
import com.jtliu.dormitorymanagement.repository.GuestRepository;
import com.jtliu.dormitorymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuestService {
    private final GuestRepository guestRepository;
    private final UserRepository userRepository;

    public GuestRecord checkin(GuestRecordVo guest) {
        if (guest == null) {
            return null;
        }
        GuestRecord guestRecord = new GuestRecord();
        guestRecord.setName(guest.getName());
        guestRecord.setPhone(guest.getPhone());
        guestRecord.setReason(guest.getReason());
        User host = userRepository.findById(guest.getHost()).orElse(null);
        if (host == null) return null;
        guestRecord.setHostName(host.getName());
        guestRecord.setHostPhone(host.getPhone());
        guestRecord.setCheckin(new Date());
        return guestRepository.save(guestRecord);
//        return new GuestRecordVo(res);
    }

    public List<GuestRecord> searchAll() {
        return guestRepository.findAll();
    }

    public List<GuestRecord> searchByPhone(String phone) {
        if (phone == null) return null;
        return guestRepository.findByPhone(phone);
    }

    public List<GuestRecord> searchCheckoutByPhone(String phone) {
        if (phone == null) return null;
        return guestRepository.findByCheckoutIsNullAndPhone(phone);
    }

    public GuestRecord checkout(Integer id) {
        if (id == null) return null;
        Optional<GuestRecord> guest = guestRepository.findById(id);
        if (guest.isPresent()) {
            GuestRecord res = guest.get();
            res.setCheckout(new Date());
            res = guestRepository.save(res);
            return res;
        } else {
            return null;
        }
    }
}
