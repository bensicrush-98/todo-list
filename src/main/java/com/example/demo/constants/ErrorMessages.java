package com.example.demo.constants;

public class ErrorMessages {
    // User-related error messages
    public static final String USER_NOT_FOUND_ERROR = "User not found.";
    public static final String USER_ALREADY_EXISTS_ERROR = "User already exists.";
    public static final String INVALID_CREDENTIALS_ERROR = "Incorrect username or password.";
    public static final String INVALID_EMAIL_ERROR = "Email is not valid.";

    // Task-related error messages
    public static final String TASK_TITLE_EMPTY_ERROR = "The title cannot be empty.";
    public static final String TASK_TITLE_SHORT_ERROR = "The title must be at least 3 characters long.";
    public static final String TASK_TITLE_LONG_ERROR = "The title must not exceed 100 characters.";
    public static final String TASK_DUEDATE_PAST_ERROR = "The due date must be in the future.";
    public static final String TASK_DESCRIPTION_EMPTY_ERROR = "The description cannot be empty.";
    public static final String TASK_DESCRIPTION_SHORT_ERROR = "The description must be at least 10 characters long.";
    public static final String TASK_DESCRIPTION_LONG_ERROR = "The description must not exceed 500 characters.";
}
