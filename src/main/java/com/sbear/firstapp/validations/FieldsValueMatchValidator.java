package com.sbear.firstapp.validations;

import com.sbear.firstapp.annotations.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
    String field1, field2;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        field1 = constraintAnnotation.field1();
        field2 = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        // BeanWrapperImpl is a Spring utility class that helps access Java bean properties via reflection
        // Reflection is a feature in the Java programming language.
        // It allows an executing Java program to examine or "introspect" upon itself, and manipulate internal properties of the program.
        // For example, it's possible for a Java class to obtain the names of all its members and display them.

        /*
        * USAGE:-
        * public class User {
            private String name;

            public User(String name) {
                this.name = name;
            }
        }
        * import org.springframework.beans.BeanWrapperImpl;

            public class BeanWrapperExample {
                public static void main(String[] args) {
                    User user = new User("John Doe");

                    // Create BeanWrapperImpl for the non-bean class
                    BeanWrapperImpl wrapper = new BeanWrapperImpl(user);

                    // Access the 'name' property using reflection (field name must match)
                    Object name = wrapper.getPropertyValue("name");

                    System.out.println("Name: " + name);  // Output: Name: John Doe
                }
            }
        */
        Object field1Value = new BeanWrapperImpl(o).getPropertyValue(field1);
        Object field2Value = new BeanWrapperImpl(o).getPropertyValue(field2);
        if(field1Value != null) {
            return field1Value.equals(field2Value);
        }
        return field2Value == null;
    }
}
