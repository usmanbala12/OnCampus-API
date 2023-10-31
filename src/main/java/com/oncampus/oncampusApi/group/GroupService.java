package com.oncampus.oncampusApi.group;

import com.oncampus.oncampusApi.system.exception.ObjectNotFoundException;
import com.oncampus.oncampusApi.user.MyUserPrincipal;
import com.oncampus.oncampusApi.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Transient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findById(Integer groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> new ObjectNotFoundException("group", groupId));
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group update(Group update, Integer groupId) {
        return groupRepository.findById(groupId)
                .map(group -> {
                   group.setName(update.getName());
                   group.setDescription(update.getDescription());
                   return groupRepository.save(group);
                }).orElseThrow(() -> new ObjectNotFoundException("group", groupId));
    }

    public void delete(Integer groupId) {
        groupRepository.findById(groupId).orElseThrow(() -> new ObjectNotFoundException("group", groupId));
        groupRepository.deleteById(groupId);
    }

    public void joinGroup(Integer groupId, User user) {
         groupRepository.findById(groupId)
                .map(group -> {
                    group.addMember(user);
                    return null;
                }).orElseThrow(() -> new ObjectNotFoundException("Group", groupId));
    }

    public void leaveGroup(Integer groupId, User user) {
        groupRepository.findById(groupId)
                .map(group -> {
                    group.removeMember(user);
                    return null;
                }).orElseThrow(() -> new ObjectNotFoundException("Group", groupId));
    }
}
