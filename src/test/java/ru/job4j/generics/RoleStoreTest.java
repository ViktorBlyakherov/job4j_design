package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Admin");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.add(new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceThenRolenameIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("1", new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("User");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("10", new Role("10", "User"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Admin");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Admin");
    }
}