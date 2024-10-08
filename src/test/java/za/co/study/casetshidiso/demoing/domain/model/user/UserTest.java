package za.co.study.casetshidiso.demoing.domain.model.user;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    private User constructUser() {
        return new User("test user", "test@user.com");
    }

    @Test
    public void testUserConstruction() {
        User user = constructUser();

        assertThat(user.getName()).isEqualTo("test user");
        assertThat(user.getEmailAddress()).isEqualTo("test@user.com");
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void testUserConstructionWithNullName() {
        User user = new User(null, "test@user.com");

        assertThat(user.getName()).isNull();
        assertThat(user.getEmailAddress()).isNotNull();
    }
}