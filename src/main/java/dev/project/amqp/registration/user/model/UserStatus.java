package dev.project.amqp.registration.user.model;

public enum UserStatus {
  CREATED("Created"),
  EMAIL_VERIFIED("Email Verified");

  private final String label;

  UserStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
