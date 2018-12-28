package com.mnowak.cirriculumvitae.model;

import java.io.Serializable;
import java.util.List;

public class SkillsViewModel implements Serializable {
    public String label;
    public String labelColor;
    public List<String> goodLevelSkills;
    public List<String> mediumLevelSkills;
    public List<String> lowLevelSkills;

}
