package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Todo type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Todos", type = Model.Type.USER, version = 1)
public final class Todo implements Model {
  public static final QueryField ID = field("Todo", "id");
  public static final QueryField PLANT = field("Todo", "plant");
  public static final QueryField PNAME = field("Todo", "pname");
  public static final QueryField COL1 = field("Todo", "col1");
  public static final QueryField COL2 = field("Todo", "col2");
  public static final QueryField COL3 = field("Todo", "col3");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String plant;
  private final @ModelField(targetType="String") String pname;
  private final @ModelField(targetType="Int") Integer col1;
  private final @ModelField(targetType="Int") Integer col2;
  private final @ModelField(targetType="Int") Integer col3;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getPlant() {
      return plant;
  }
  
  public String getPname() {
      return pname;
  }
  
  public Integer getCol1() {
      return col1;
  }
  
  public Integer getCol2() {
      return col2;
  }
  
  public Integer getCol3() {
      return col3;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Todo(String id, String plant, String pname, Integer col1, Integer col2, Integer col3) {
    this.id = id;
    this.plant = plant;
    this.pname = pname;
    this.col1 = col1;
    this.col2 = col2;
    this.col3 = col3;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Todo todo = (Todo) obj;
      return ObjectsCompat.equals(getId(), todo.getId()) &&
              ObjectsCompat.equals(getPlant(), todo.getPlant()) &&
              ObjectsCompat.equals(getPname(), todo.getPname()) &&
              ObjectsCompat.equals(getCol1(), todo.getCol1()) &&
              ObjectsCompat.equals(getCol2(), todo.getCol2()) &&
              ObjectsCompat.equals(getCol3(), todo.getCol3()) &&
              ObjectsCompat.equals(getCreatedAt(), todo.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), todo.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPlant())
      .append(getPname())
      .append(getCol1())
      .append(getCol2())
      .append(getCol3())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Todo {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("plant=" + String.valueOf(getPlant()) + ", ")
      .append("pname=" + String.valueOf(getPname()) + ", ")
      .append("col1=" + String.valueOf(getCol1()) + ", ")
      .append("col2=" + String.valueOf(getCol2()) + ", ")
      .append("col3=" + String.valueOf(getCol3()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static PlantStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Todo justId(String id) {
    return new Todo(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      plant,
      pname,
      col1,
      col2,
      col3);
  }
  public interface PlantStep {
    BuildStep plant(String plant);
  }
  

  public interface BuildStep {
    Todo build();
    BuildStep id(String id);
    BuildStep pname(String pname);
    BuildStep col1(Integer col1);
    BuildStep col2(Integer col2);
    BuildStep col3(Integer col3);
  }
  

  public static class Builder implements PlantStep, BuildStep {
    private String id;
    private String plant;
    private String pname;
    private Integer col1;
    private Integer col2;
    private Integer col3;
    @Override
     public Todo build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Todo(
          id,
          plant,
          pname,
          col1,
          col2,
          col3);
    }
    
    @Override
     public BuildStep plant(String plant) {
        Objects.requireNonNull(plant);
        this.plant = plant;
        return this;
    }
    
    @Override
     public BuildStep pname(String pname) {
        this.pname = pname;
        return this;
    }
    
    @Override
     public BuildStep col1(Integer col1) {
        this.col1 = col1;
        return this;
    }
    
    @Override
     public BuildStep col2(Integer col2) {
        this.col2 = col2;
        return this;
    }
    
    @Override
     public BuildStep col3(Integer col3) {
        this.col3 = col3;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String plant, String pname, Integer col1, Integer col2, Integer col3) {
      super.id(id);
      super.plant(plant)
        .pname(pname)
        .col1(col1)
        .col2(col2)
        .col3(col3);
    }
    
    @Override
     public CopyOfBuilder plant(String plant) {
      return (CopyOfBuilder) super.plant(plant);
    }
    
    @Override
     public CopyOfBuilder pname(String pname) {
      return (CopyOfBuilder) super.pname(pname);
    }
    
    @Override
     public CopyOfBuilder col1(Integer col1) {
      return (CopyOfBuilder) super.col1(col1);
    }
    
    @Override
     public CopyOfBuilder col2(Integer col2) {
      return (CopyOfBuilder) super.col2(col2);
    }
    
    @Override
     public CopyOfBuilder col3(Integer col3) {
      return (CopyOfBuilder) super.col3(col3);
    }
  }
  
}
