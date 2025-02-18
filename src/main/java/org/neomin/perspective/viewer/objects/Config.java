package org.neomin.perspective.viewer.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Config {

    @Expose(serialize = false)
    private boolean active;

    @Expose(serialize = false)
    private boolean modifiedVertex, modifiedEdge;

    @Expose(serialize = false)
    private int latestVertex, latestEdge;

    @SerializedName("Vertex")
    private int[][] vertex = new int[100][];

    @SerializedName("Edges")
    private int[][] edges = new int[100][];

}
