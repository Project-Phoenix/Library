/*
 * Copyright (C) 2013 Project-Phoenix
 * 
 * This file is part of library.
 * 
 * library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with library.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.phoenix.database.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "exerciseGroup")
@XmlRootElement
//@formatter:off
@NamedQueries({
    @NamedQuery(name = "ExerciseGroup.findAll", query = "SELECT e FROM ExerciseGroup e"),
    @NamedQuery(name = "ExerciseGroup.findById", query = "SELECT e FROM ExerciseGroup e WHERE e.id = :id"),
    @NamedQuery(name = "ExerciseGroup.findByName", query = "SELECT e FROM ExerciseGroup e WHERE e.name = :name"),
    @NamedQuery(name = "ExerciseGroup.findByRoom", query = "SELECT e FROM ExerciseGroup e WHERE e.room = :room"),
    @NamedQuery(name = "ExerciseGroup.findByTurnus", query = "SELECT e FROM ExerciseGroup e WHERE e.turnus = :turnus"),
    @NamedQuery(name = "ExerciseGroup.findBySubmissionExpireDate", query = "SELECT e FROM ExerciseGroup e WHERE e.submissionExpireDate = :submissionExpireDate"),
    @NamedQuery(name = "ExerciseGroup.findByRegistrationStartDate", query = "SELECT e FROM ExerciseGroup e WHERE e.registrationStartDate = :registrationStartDate"),
    @NamedQuery(name = "ExerciseGroup.findByRegistrationEndDate", query = "SELECT e FROM ExerciseGroup e WHERE e.registrationEndDate = :registrationEndDate")})
//@formatter:on
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class ExerciseGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "room")
    private String room;

    @Basic(optional = false)
    @Column(name = "turnus")
    private String turnus;

    @Column(name = "submission_expire_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionExpireDate;

    @Basic(optional = false)
    @Column(name = "registration_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationStartDate;

    @Basic(optional = false)
    @Column(name = "registration_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationEndDate;

    @ManyToMany(mappedBy = "exerciseGroupList")
    private List<User> userList;

    @JoinTable(name = "groupMaterial", joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "material_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Material> materialList;

    @JsonBackReference("user-exerciseGroup")
    @JoinColumn(name = "exerciseLeader", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User exerciseLeader;

    @JsonBackReference("lecture-exerciseGroup")
    @JoinColumn(name = "lecture", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lecture lecture;

    @JsonManagedReference("exerciseGroup-exerciseSheet")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseGroup")
    private List<ExerciseSheet> exerciseSheetList;

    public ExerciseGroup() {
    }

    public ExerciseGroup(Integer id) {
        this.id = id;
    }

    public ExerciseGroup(Integer id, String name, String room, String turnus, Date registrationStartDate, Date registrationEndDate) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.turnus = turnus;
        this.registrationStartDate = registrationStartDate;
        this.registrationEndDate = registrationEndDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTurnus() {
        return turnus;
    }

    public void setTurnus(String turnus) {
        this.turnus = turnus;
    }

    public Date getSubmissionExpireDate() {
        return submissionExpireDate;
    }

    public void setSubmissionExpireDate(Date submissionExpireDate) {
        this.submissionExpireDate = submissionExpireDate;
    }

    public Date getRegistrationStartDate() {
        return registrationStartDate;
    }

    public void setRegistrationStartDate(Date registrationStartDate) {
        this.registrationStartDate = registrationStartDate;
    }

    public Date getRegistrationEndDate() {
        return registrationEndDate;
    }

    public void setRegistrationEndDate(Date registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    public List<User> getGroupMember() {
        return userList;
    }

    public void setGroupMember(List<User> groupMember) {
        this.userList = groupMember;
    }

    public List<Material> getMaterials() {
        return materialList;
    }

    public void setMaterials(List<Material> materials) {
        this.materialList = materials;
    }

    public User getExerciseLeader() {
        return exerciseLeader;
    }

    public void setExerciseLeader(User exerciseLeader) {
        this.exerciseLeader = exerciseLeader;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public List<ExerciseSheet> getExerciseSheets() {
        return exerciseSheetList;
    }

    public void setExerciseSheets(List<ExerciseSheet> exerciseSheets) {
        this.exerciseSheetList = exerciseSheets;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof ExerciseGroup)) {
            return false;
        }
        ExerciseGroup other = (ExerciseGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.phoenix.database.entity.ExerciseGroup[ id=" + id + " ]";
    }

}
