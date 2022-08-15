package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("work")).isEqualTo("Home=1");
        assertThat(config.value("comm")).isEqualTo(null);
        assertThat(config.values).hasSize(3);
    }

    @Test
    void whenOnlyCommentAndBlank() {
        String path = "./data/comments_and_blank.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.values).hasSize(0);
    }

    @Test
    void whenHasInvalidPair() {
        String path = "./data/pair_with_invalid.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

}