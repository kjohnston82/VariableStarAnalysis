/*
 * Copyright (C) 2016 Kyle Johnston
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fit.astro.vsa.common.utilities.math.geometry.convex;

/**
 * Maintains a list of vertices for use by QuickHull3D
 *
 * @author Kyle Johnston <kyjohnst2000@my.fit.edu> (Edits and Updates)
 */
public class VertexList {

    private Vertex head;
    private Vertex tail;

    /**
     * Clears this list.
     */
    public void clear() {
        head = tail = null;
    }

    /**
     * Adds a vertex to the end of this list.
     *
     * @param vtx
     */
    public void add(Vertex vtx) {
        if (head == null) {
            head = vtx;
        } else {
            tail.next = vtx;
        }
        vtx.prev = tail;
        vtx.next = null;
        tail = vtx;
    }

    /**
     * Adds a chain of vertices to the end of this list.
     *
     * @param vtx
     */
    public void addAll(Vertex vtx) {
        if (head == null) {
            head = vtx;
        } else {
            tail.next = vtx;
        }
        vtx.prev = tail;
        while (vtx.next != null) {
            vtx = vtx.next;
        }
        tail = vtx;
    }

    /**
     * Deletes a vertex from this list.
     *
     * @param vtx
     */
    public void delete(Vertex vtx) {
        if (vtx.prev == null) {
            head = vtx.next;
        } else {
            vtx.prev.next = vtx.next;
        }
        if (vtx.next == null) {
            tail = vtx.prev;
        } else {
            vtx.next.prev = vtx.prev;
        }
    }

    /**
     * Deletes a chain of vertices from this list.
     *
     * @param vtx1
     * @param vtx2
     */
    public void delete(Vertex vtx1, Vertex vtx2) {
        if (vtx1.prev == null) {
            head = vtx2.next;
        } else {
            vtx1.prev.next = vtx2.next;
        }
        if (vtx2.next == null) {
            tail = vtx1.prev;
        } else {
            vtx2.next.prev = vtx1.prev;
        }
    }

    /**
     * Inserts a vertex into this list before another specificed vertex.
     *
     * @param vtx
     * @param next
     */
    public void insertBefore(Vertex vtx, Vertex next) {
        vtx.prev = next.prev;
        if (next.prev == null) {
            head = vtx;
        } else {
            next.prev.next = vtx;
        }
        vtx.next = next;
        next.prev = vtx;
    }

    /**
     * Returns the first element in this list.
     *
     * @return
     */
    public Vertex first() {
        return head;
    }

    /**
     * Returns true if this list is empty.
     *
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }
}
