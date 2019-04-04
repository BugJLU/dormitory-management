package com.jtliu.dormitorymanagement.service;

import com.jtliu.dormitorymanagement.model.Notice;
import com.jtliu.dormitorymanagement.model.User;
import com.jtliu.dormitorymanagement.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserService userService;

    public Notice saveNotice(String message, User user) {
        if (user == null || user.getRole() != 0) return null;
        user = userService.searchById(user.getId());
        Notice notice = new Notice();
        notice.setAdmin(user);
        notice.setMessage(message);
        notice.setDate(new Date());
        return noticeRepository.save(notice);
    }

    public Notice getLastNotice() {
        List<Notice> notices = noticeRepository.findAll();
        if (notices == null || notices.size() == 0) return null;
        Notice notice = notices.get(0);
        for (Notice n :
                notices) {
            if (n.getDate().after(notice.getDate())) {
                notice = n;
            }
        }
        return notice;
    }

    public List<Notice> getNotices() {
        return noticeRepository.findAll();
    }
}
