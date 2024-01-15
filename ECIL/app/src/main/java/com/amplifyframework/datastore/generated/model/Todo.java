package com.amplifyframework.datastore.generated.model;


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
@Index(name = "byId", fields = {"cnt","id"})
public final class Todo implements Model {
  public static final QueryField ID = field("Todo", "id");
  public static final QueryField PLANT = field("Todo", "plant");
  public static final QueryField PNAME = field("Todo", "pname");
  public static final QueryField COL1 = field("Todo", "col1");
  public static final QueryField COL2 = field("Todo", "col2");
  public static final QueryField COL3 = field("Todo", "col3");
  public static final QueryField CNT = field("Todo", "cnt");
  public static final QueryField CREATED_AT = field("Todo", "createdAt");
  public static final QueryField UPDATED_AT = field("Todo", "updatedAt");
  private final @ModelField(targetType="String", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String plant;
  private final @ModelField(targetType="String") String pname;
  private final @ModelField(targetType="String") String col1;
  private final @ModelField(targetType="String") String col2;
  private final @ModelField(targetType="Int") Integer col3;
  private final @ModelField(targetType="Int", isRequired = true) Integer cnt;
  private final @ModelField(targetType="String", isRequired = true) String createdAt;
  private final @ModelField(targetType="String", isRequired = true) String updatedAt;
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
  
  public String getCol1() {
      return col1;
  }
  
  public String getCol2() {
      return col2;
  }
  
  public Integer getCol3() {
      return col3;
  }
  
  public Integer getCnt() {
      return cnt;
  }
  
  public String getCreatedAt() {
      return createdAt;
  }
  
  public String getUpdatedAt() {
      return updatedAt;
  }
  
  private Todo(String id, String plant, String pname, String col1, String col2, Integer col3, Integer cnt, String createdAt, String updatedAt) {
    this.id = id;
    this.plant = plant;
    this.pname = pname;
    this.col1 = col1;
    this.col2 = col2;
    this.col3 = col3;
    this.cnt = cnt;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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
              ObjectsCompat.equals(getCnt(), todo.getCnt()) &&
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
      .append(getCnt())
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
      .append("cnt=" + String.valueOf(getCnt()) + ", ")
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
      col3,
      cnt,
      createdAt,
      updatedAt);
  }
  public interface PlantStep {
    CntStep plant(String plant);
  }
  

  public interface CntStep {
    CreatedAtStep cnt(Integer cnt);
  }
  

  public interface CreatedAtStep {
    UpdatedAtStep createdAt(String createdAt);
  }
  

  public interface UpdatedAtStep {
    BuildStep updatedAt(String updatedAt);
  }
  

  public interface BuildStep {
    Todo build();
    BuildStep id(String id);
    BuildStep pname(String pname);
    BuildStep col1(String col1);
    BuildStep col2(String col2);
    BuildStep col3(Integer col3);
  }
  

  public static class Builder implements PlantStep, CntStep, CreatedAtStep, UpdatedAtStep, BuildStep {
    private String id;
    private String plant;
    private Integer cnt;
    private String createdAt;
    private String updatedAt;
    private String pname;
    private String col1;
    private String col2;
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
          col3,
          cnt,
          createdAt,
          updatedAt);
    }
    
    @Override
     public CntStep plant(String plant) {
        Objects.requireNonNull(plant);
        this.plant = plant;
        return this;
    }
    
    @Override
     public CreatedAtStep cnt(Integer cnt) {
        Objects.requireNonNull(cnt);
        this.cnt = cnt;
        return this;
    }
    
    @Override
     public UpdatedAtStep createdAt(String createdAt) {
        Objects.requireNonNull(createdAt);
        this.createdAt = createdAt;
        return this;
    }
    
    @Override
     public BuildStep updatedAt(String updatedAt) {
        Objects.requireNonNull(updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }
    
    @Override
     public BuildStep pname(String pname) {
        this.pname = pname;
        return this;
    }
    
    @Override
     public BuildStep col1(String col1) {
        this.col1 = col1;
        return this;
    }
    
    @Override
     public BuildStep col2(String col2) {
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
    private CopyOfBuilder(String id, String plant, String pname, String col1, String col2, Integer col3, Integer cnt, String createdAt, String updatedAt) {
      super.id(id);
      super.plant(plant)
        .cnt(cnt)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
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
     public CopyOfBuilder cnt(Integer cnt) {
      return (CopyOfBuilder) super.cnt(cnt);
    }
    
    @Override
     public CopyOfBuilder createdAt(String createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
    }
    
    @Override
     public CopyOfBuilder updatedAt(String updatedAt) {
      return (CopyOfBuilder) super.updatedAt(updatedAt);
    }
    
    @Override
     public CopyOfBuilder pname(String pname) {
      return (CopyOfBuilder) super.pname(pname);
    }
    
    @Override
     public CopyOfBuilder col1(String col1) {
      return (CopyOfBuilder) super.col1(col1);
    }
    
    @Override
     public CopyOfBuilder col2(String col2) {
      return (CopyOfBuilder) super.col2(col2);
    }
    
    @Override
     public CopyOfBuilder col3(Integer col3) {
      return (CopyOfBuilder) super.col3(col3);
    }
  }
  
}
