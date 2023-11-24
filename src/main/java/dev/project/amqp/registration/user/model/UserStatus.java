package dev.project.amqp.registration.user.model;

public enum UserStatus {
  PENDING_VERIFICATION("Pending verification"),
  VERIFIED("Verified");

  private final String label;

  UserStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
