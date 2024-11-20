package org.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utils.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.pages.PasswordGeneratorPage;

public class PasswordGeneratorTest extends BaseTest {

    private PasswordGeneratorPage passwordPage;

    @BeforeMethod
    public void initPageObject() {
        passwordPage = new PasswordGeneratorPage(driver);
    }

    @Test
    public void testPasswordLength() {
    	System.out.println("testPasswordLength");
        int expectedLength = 12;
        passwordPage.setPasswordLength(expectedLength);
        passwordPage.selectCharacterOptions(true, true, true, true);
        passwordPage.generatePassword();

        String password = passwordPage.getPassword();
        assertThat(password.length()).isEqualTo(expectedLength);
    }

    @Test
    public void testCharacterOptions() {
        passwordPage.setPasswordLength(10);
        passwordPage.selectCharacterOptions(true, false, false, true); // Only uppercase and symbols
        passwordPage.generatePassword();

        String password = passwordPage.getPassword();
        boolean valid = passwordPage.validatePasswordCriteria(password, true, false, false, true);
        assertThat(valid).isTrue();
    }

    @Test
    public void testPasswordUniqueness() {
        passwordPage.setPasswordLength(10);
        passwordPage.selectCharacterOptions(true, true, true, true);

        passwordPage.generatePassword();
        String password1 = passwordPage.getPassword();

        passwordPage.generatePassword();
        String password2 = passwordPage.getPassword();

        assertThat(password1).isNotEqualTo(password2); // Passwords should be unique
    }

    @Test
    public void testCopyFunctionality() {
        passwordPage.setPasswordLength(10);
        passwordPage.selectCharacterOptions(true, true, true, true);
        passwordPage.generatePassword();

        String password = passwordPage.getPassword();
        passwordPage.copyPassword();

        String clipboardContent = ClipboardUtil.getClipboardContent();
        assertThat(clipboardContent).isEqualTo(password);
    }
}
