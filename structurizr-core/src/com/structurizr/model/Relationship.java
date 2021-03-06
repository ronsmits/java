package com.structurizr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Relationship extends TaggableThing {

    protected String id = "";

    private Element source;
    private String sourceId;
    private Element destination;
    private String destinationId;
    private String description;
    private String technology;
    private InteractionStyle interactionStyle = InteractionStyle.Synchronous;

    Relationship() {
        addTags(Tags.RELATIONSHIP);
    }

    Relationship(Element source, Element destination, String description) {
        this(source, destination, description, null);
    }

    Relationship(Element source, Element destination, String description, String technology) {
        this(source, destination, description, technology, InteractionStyle.Synchronous);
    }

    Relationship(Element source, Element destination, String description, String technology, InteractionStyle interactionStyle) {
        this();

        this.source = source;
        this.destination = destination;
        this.description = description;
        this.technology = technology;
        setInteractionStyle(interactionStyle);
    }

    @JsonIgnore
    public Element getSource() {
        return source;
    }

    public String getSourceId() {
        if (this.source != null) {
            return this.source.getId();
        } else {
            return this.sourceId;
        }
    }

    public String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setSource(Element source) {
        this.source = source;
    }

    @JsonIgnore
    public Element getDestination() {
        return destination;
    }

    public String getDestinationId() {
        if (this.destination != null) {
            return this.destination.getId();
        } else {
            return this.destinationId;
        }
    }

    void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public void setDestination(Element destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description != null ? description : "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public InteractionStyle getInteractionStyle() {
        return interactionStyle;
    }

    public void setInteractionStyle(InteractionStyle interactionStyle) {
        this.interactionStyle = interactionStyle;

        if (interactionStyle == InteractionStyle.Synchronous) {
            removeTag(Tags.ASYNCHRONOUS);
            addTags(Tags.SYNCHRONOUS);
        } else {
            removeTag(Tags.SYNCHRONOUS);
            addTags(Tags.ASYNCHRONOUS);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relationship that = (Relationship) o;

        if (!getDescription().equals(that.getDescription())) return false;
        if (!getDestination().equals(that.getDestination())) return false;
        if (!getSource().equals(that.getSource())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getSourceId().hashCode();
        result = 31 * result + getDestinationId().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return source.toString() + " ---[" + description + "]---> " + destination.toString();
    }

}