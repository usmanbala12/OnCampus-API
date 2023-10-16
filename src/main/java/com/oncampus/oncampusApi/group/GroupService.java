package com.oncampus.oncampusApi.group;

import com.oncampus.oncampusApi.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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

}
