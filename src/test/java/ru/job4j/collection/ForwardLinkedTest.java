package ru.job4j.collection;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;
    private ForwardLinked<Integer> linkedRevert;

    @BeforeEach
    public void init() {
        linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);

        linkedRevert = new ForwardLinked<>();
    }

    @Test
    void whenSize0ThenReturnFalse() {
        assertThat(linkedRevert.revert()).isFalse();
    }

    @Test
    void whenSize1ThenReturnFalse() {
        linked.add(1);
        assertThat(linkedRevert.revert()).isFalse();
    }

    @Test
    void whenAddAndRevertTrue() {
        linkedRevert.add(1);
        linkedRevert.add(2);
        linkedRevert.add(3);
        linkedRevert.add(4);
        assertThat(linkedRevert).containsSequence(1, 2, 3, 4);
        assertThat(linkedRevert.revert()).isTrue();
        assertThat(linkedRevert).containsSequence(4, 3, 2, 1);
    }

    @Test
    void whenDeleteFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
        assertThatThrownBy(linked.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThatThrownBy(linked::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiDelete() {
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(2);
    }
}