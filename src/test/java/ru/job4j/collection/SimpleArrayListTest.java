package ru.job4j.collection;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.IntStream;

class SimpleArrayListTest {

    private SimpleList<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    void checkIterator() {
        Assertions.assertThat(list.size()).isEqualTo(3);
        Assertions.assertThat(list).hasSize(3);
    }

    @Test
    void whenAddThenSizeIncrease() {
        list.add(4);
        Assertions.assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void whenRemoveThenGetValueAndSizeDecrease() {
        Assertions.assertThat(list.remove(1)).isEqualTo(2);
        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        Assertions.assertThat(list.get(0)).isEqualTo(1);
        Assertions.assertThat(list.get(1)).isEqualTo(3);
    }

    @Test
    void whenAddAndGetByCorrectIndex() {
        list.add(4);
        Assertions.assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetByIncorrectIndexThenGetException() {
        Assertions.assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAndAndGetByIncorrectIndexThenGetException() {
        SimpleList<Integer> list = new SimpleArrayList<>(10);
        list.add(5);
        Assertions.assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveByIncorrectIndexThenGetException() {
        Assertions.assertThatThrownBy(() -> list.remove(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        Assertions.assertThat(list.size()).isEqualTo(2);
        Assertions.assertThat(list.get(0)).isNull();
        Assertions.assertThat(list.get(1)).isNull();
    }

    @Test
    void whenSetThenGetOldValueAndSizeNotChanged() {
        Assertions.assertThat(list.set(1, 22)).isEqualTo(2);
        Assertions.assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void whenSetByIncorrectIndexThenGetException() {
        Assertions.assertThatThrownBy(() -> list.set(5, 22))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        Assertions.assertThat(list.iterator().hasNext()).isFalse();
    }

    @Test
    void whenGetIteratorFromEmptyListThenNextThrowException() {
        list = new SimpleArrayList<>(5);
        Assertions.assertThatThrownBy(list.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Assertions.assertThat(list.iterator().next()).isEqualTo(1);
        Assertions.assertThat(list.iterator().next()).isEqualTo(1);
    }

    @Test
    void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        Assertions.assertThat(iterator.hasNext()).isTrue();
        Assertions.assertThat(iterator.next()).isEqualTo(1);
        Assertions.assertThat(iterator.hasNext()).isTrue();
        Assertions.assertThat(iterator.next()).isEqualTo(2);
        Assertions.assertThat(iterator.hasNext()).isTrue();
        Assertions.assertThat(iterator.next()).isEqualTo(3);
        Assertions.assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenNoPlaceThenMustIncreaseCapacity() {
        Assertions.assertThat(list.size()).isEqualTo(3);
        IntStream.range(3, 10).forEach(v -> list.add(v));
        Assertions.assertThat(list.size()).isEqualTo(10);
    }

    @Test
    void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        Assertions.assertThatThrownBy(iterator::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.remove(0);
        Assertions.assertThatThrownBy(iterator::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenSetAfterGetIteratorThenMustBeOk() {
        Iterator<Integer> iterator = list.iterator();
        list.set(0, 22);
        Assertions.assertThat(iterator.next()).isEqualTo(22);
    }
}