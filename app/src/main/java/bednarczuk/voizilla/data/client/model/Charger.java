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
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Ładowarka
 */
@ApiModel(description = "Ładowarka")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2018-07-22T20:17:00.244187+02:00[Europe/Warsaw]")
public class Charger {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id = null;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name = null;

  public static final String SERIALIZED_NAME_LOCATION = "location";
  @SerializedName(SERIALIZED_NAME_LOCATION)
  private GeoPoint location = null;

  public static final String SERIALIZED_NAME_ADDRESS = "address";
  @SerializedName(SERIALIZED_NAME_ADDRESS)
  private MapAddress address = null;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private Type type = null;

  public static final String SERIALIZED_NAME_PARKING_ID = "parkingId";
  @SerializedName(SERIALIZED_NAME_PARKING_ID)
  private UUID parkingId = null;

  public Charger id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Identyfikator
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identyfikator")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Charger name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Nazwa
   * @return name
  **/
  @ApiModelProperty(value = "Nazwa")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Charger location(GeoPoint location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")
  public GeoPoint getLocation() {
    return location;
  }

  public void setLocation(GeoPoint location) {
    this.location = location;
  }

  public Charger address(MapAddress address) {
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")
  public MapAddress getAddress() {
    return address;
  }

  public void setAddress(MapAddress address) {
    this.address = address;
  }

  public Charger type(Type type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Charger parkingId(UUID parkingId) {
    this.parkingId = parkingId;
    return this;
  }

   /**
   * Id parkingu na którym jest ładowarka
   * @return parkingId
  **/
  @ApiModelProperty(value = "Id parkingu na którym jest ładowarka")
  public UUID getParkingId() {
    return parkingId;
  }

  public void setParkingId(UUID parkingId) {
    this.parkingId = parkingId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Charger charger = (Charger) o;
    return Objects.equals(this.id, charger.id) &&
        Objects.equals(this.name, charger.name) &&
        Objects.equals(this.location, charger.location) &&
        Objects.equals(this.address, charger.address) &&
        Objects.equals(this.type, charger.type) &&
        Objects.equals(this.parkingId, charger.parkingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, location, address, type, parkingId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Charger {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    parkingId: ").append(toIndentedString(parkingId)).append("\n");
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

