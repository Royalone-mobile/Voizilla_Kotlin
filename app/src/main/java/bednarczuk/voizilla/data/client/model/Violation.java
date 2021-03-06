/*
 * API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * OpenAPI spec version: v0.11.12
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package bednarczuk.voizilla.data.client.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Violation
 */
@ApiModel(description = "Violation")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2018-07-22T20:17:00.244187+02:00[Europe/Warsaw]")
public class Violation {
  public static final String SERIALIZED_NAME_FIELD = "field";
  @SerializedName(SERIALIZED_NAME_FIELD)
  private String field = null;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private String type = null;

  public static final String SERIALIZED_NAME_MESSAGE = "message";
  @SerializedName(SERIALIZED_NAME_MESSAGE)
  private String message = null;

  public Violation field(String field) {
    this.field = field;
    return this;
  }

   /**
   * Field
   * @return field
  **/
  @ApiModelProperty(required = true, value = "Field")
  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public Violation type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "Type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Violation message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Message
   * @return message
  **/
  @ApiModelProperty(required = true, value = "Message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Violation violation = (Violation) o;
    return Objects.equals(this.field, violation.field) &&
        Objects.equals(this.type, violation.type) &&
        Objects.equals(this.message, violation.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, type, message);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Violation {\n");

    sb.append("    field: ").append(toIndentedString(field)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

