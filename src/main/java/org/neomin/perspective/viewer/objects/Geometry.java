package org.neomin.perspective.viewer.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.google.gson.annotations.SerializedName;

@Getter @Setter
@NoArgsConstructor
public class Geometry {

    @SerializedName("Vertex")
    private int[][] vertex;

    @SerializedName("Edges")
    private int[][] edges;

    @SerializedName("Faces")
    private int[][] faces;

    @SerializedName("Color")
    private int[][] colors;
}
