package me.rcortesb.user.repository;

import me.rcortesb.user.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Short> {
    Tag findByTagName(String tagName);
}
