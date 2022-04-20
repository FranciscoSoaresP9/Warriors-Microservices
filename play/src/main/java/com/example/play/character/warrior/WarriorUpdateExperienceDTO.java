package com.example.play.character.warrior;
/**
 * A warriorUpdateExperienceDTO to help with the leveling up process
 */
public class WarriorUpdateExperienceDTO {
   private Integer id;
   private int experience;

    public WarriorUpdateExperienceDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

}
