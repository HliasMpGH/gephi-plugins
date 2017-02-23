package net.clementlevallois.ForceAtlas3D;

/*
 Copyright 2008-2011 Gephi
 Authors : Mathieu Jacomy <mathieu.jacomy@gmail.com>
 Website : http://www.gephi.org

 This file is part of Gephi.

 DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

 Copyright 2011 Gephi Consortium. All rights reserved.

 The contents of this file are subject to the terms of either the GNU
 General Public License Version 3 only ("GPL") or the Common
 Development and Distribution License("CDDL") (collectively, the
 "License"). You may not use this file except in compliance with the
 License. You can obtain a copy of the License at
 http://gephi.org/about/legal/license-notice/
 or /cddl-1.0.txt and /gpl-3.0.txt. See the License for the
 specific language governing permissions and limitations under the
 License.  When distributing the software, include this License Header
 Notice in each file and include the License files at
 /cddl-1.0.txt and /gpl-3.0.txt. If applicable, add the following below the
 License Header, with the fields enclosed by brackets [] replaced by
 your own identifying information:
 "Portions Copyrighted [year] [name of copyright owner]"

 If you wish your version of this file to be governed by only the CDDL
 or only the GPL Version 3, indicate your decision by adding
 "[Contributor] elects to include this software in this distribution
 under the [CDDL or GPL Version 3] license." If you do not indicate a
 single choice of license, a recipient has the option to distribute
 your version of this file under either the CDDL, the GPL Version 3 or
 to extend the choice of license to its licensees as provided above.
 However, if you add GPL Version 3 code and therefore, elected the GPL
 Version 3 license, then the option applies only if the new code is
 made subject to such option by the copyright holder.

 Contributor(s): Clement Levallois

 Portions Copyrighted 2011 Gephi Consortium.
 */


import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeData;

/**
 * Generates the forces on demand, here are all the formulas for attraction and
 * repulsion.
 *
 * @author Mathieu Jacomy
 */
public class ForceFactory {

    public static ForceFactory builder = new ForceFactory();

    private ForceFactory() {
    }

    ;

    public RepulsionForce buildRepulsion(boolean adjustBySize, double coefficient, boolean isThreeD) {
        if (adjustBySize) {
            return new linRepulsion_antiCollision(coefficient, isThreeD);
        } else {
            return new linRepulsion(coefficient, isThreeD);
        }
    }

    public RepulsionForce getStrongGravity(double coefficient, boolean isThreeD) {
        return new strongGravity(coefficient, isThreeD);
    }

    public AttractionForce buildAttraction(boolean isThreeD, boolean logAttraction, boolean distributedAttraction, boolean adjustBySize, double coefficient) {
        if (adjustBySize) {
            if (logAttraction) {
                if (distributedAttraction) {
                    return new logAttraction_degreeDistributed_antiCollision(coefficient, isThreeD);
                } else {
                    return new logAttraction_antiCollision(coefficient, isThreeD);
                }
            } else {
                if (distributedAttraction) {
                    return new linAttraction_degreeDistributed_antiCollision(coefficient, isThreeD);
                } else {
                    return new linAttraction_antiCollision(coefficient, isThreeD);
                }
            }
        } else {
            if (logAttraction) {
                if (distributedAttraction) {
                    return new logAttraction_degreeDistributed(coefficient, isThreeD);
                } else {
                    return new logAttraction(coefficient, isThreeD);
                }
            } else {
                if (distributedAttraction) {
                    return new linAttraction_massDistributed(coefficient, isThreeD);
                } else {
                    return new linAttraction(coefficient, isThreeD);
                }
            }
        }
    }

    public abstract class AttractionForce {

        public abstract void apply(Node n1, Node n2, double e); // Model for node-node attraction (e is for edge weight if needed)
    }

    public abstract class RepulsionForce {

        public abstract void apply(Node n1, Node n2);           // Model for node-node repulsion

        public abstract void apply(Node n, Region r);           // Model for Barnes Hut approximation

        public abstract void apply(Node n, double g);           // Model for gravitation (anti-repulsion)
    }

    /*
     * Repulsion force: Linear
     */
    private class linRepulsion extends RepulsionForce {

        private double coefficient;
        private boolean isThreeD;

        public linRepulsion(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;
        }

        @Override
        public void apply(Node n1, Node n2) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist);
            }

            if (distance > 0) {
                // NB: factor = force / distance
//                double factor = coefficient * n1Layout.mass * n2Layout.mass / distance / distance;
                double factor = coefficient * (1 / (Math.min(n1Layout.mass, n2Layout.mass) * Math.min(n1Layout.mass, n2Layout.mass))) / distance / distance;
                n1Layout.dx += xDist * factor;
                n1Layout.dy += yDist * factor;
                if (isThreeD) {
                    n1Layout.dz += zDist * factor;
                }
                n2Layout.dx -= xDist * factor;
                n2Layout.dy -= yDist * factor;
                if (isThreeD) {
                    n2Layout.dz -= zDist * factor;
                }
            }
        }

        @Override
        public void apply(Node n, Region r) {
            NodeData nData = n.getNodeData();
            ForceAtlas2LayoutData nLayout = nData.getLayoutData();

            // Get the distance
            double xDist = nData.x() - r.getMassCenterX();
            double yDist = nData.y() - r.getMassCenterY();
            double zDist = nData.z() - r.getMassCenterZ();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist);
            }

            if (distance > 0) {
                // NB: factor = force / distance
                double factor = coefficient * nLayout.mass * r.getMass() / distance / distance;

                nLayout.dx += xDist * factor;
                nLayout.dy += yDist * factor;
                if (isThreeD) {
                    nLayout.dz += zDist * factor;
                }
            }
        }

        @Override
        public void apply(Node n, double g) {
            NodeData nData = n.getNodeData();
            ForceAtlas2LayoutData nLayout = nData.getLayoutData();

            // Get the distance
            double xDist = nData.x();
            double yDist = nData.y();
            double zDist = nData.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist);
            }

            if (distance > 0) {
                // NB: factor = force / distance
                double factor = coefficient * nLayout.mass * g / distance;

                nLayout.dx -= xDist * factor;
                nLayout.dy -= yDist * factor;
                if (isThreeD) {
                    nLayout.dz -= zDist * factor;
                }
            }
        }
    }

    /*
     * Repulsion force: Strong Gravity (as a Repulsion Force because it is easier)
     */
    private class linRepulsion_antiCollision extends RepulsionForce {

        private double coefficient;
        private boolean isThreeD;

        public linRepulsion_antiCollision(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;

        }

        @Override
        public void apply(Node n1, Node n2) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist) - n1Data.getSize() - n2Data.getSize();
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist) - n1Data.getSize() - n2Data.getSize();
            }

            if (distance > 0) {
                // NB: factor = force / distance
                double factor = coefficient * n1Layout.mass * n2Layout.mass / distance / distance;

                n1Layout.dx += xDist * factor;
                n1Layout.dy += yDist * factor;
                if (isThreeD) {
                    n1Layout.dz += zDist * factor;
                }
                n2Layout.dx -= xDist * factor;
                n2Layout.dy -= yDist * factor;
                if (isThreeD) {
                    n2Layout.dz -= zDist * factor;
                }
            } else if (distance < 0) {
                double factor = 100 * coefficient * n1Layout.mass * n2Layout.mass;

                n1Layout.dx += xDist * factor;
                n1Layout.dy += yDist * factor;
                if (isThreeD) {
                    n1Layout.dz += zDist * factor;
                }
                n2Layout.dx -= xDist * factor;
                n2Layout.dy -= yDist * factor;
                if (isThreeD) {
                    n2Layout.dz -= zDist * factor;
                }
            }
        }

        @Override
        public void apply(Node n, Region r) {
            NodeData nData = n.getNodeData();
            ForceAtlas2LayoutData nLayout = nData.getLayoutData();

            // Get the distance
            double xDist = nData.x() - r.getMassCenterX();
            double yDist = nData.y() - r.getMassCenterY();
            double zDist = nData.z() - r.getMassCenterZ();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist);
            }


            if (distance > 0) {
                // NB: factor = force / distance
                double factor = coefficient * nLayout.mass * r.getMass() / distance / distance;

                nLayout.dx += xDist * factor;
                nLayout.dy += yDist * factor;
                if (isThreeD) {
                    nLayout.dz += zDist * factor;
                }
            } else if (distance < 0) {
                double factor = -coefficient * nLayout.mass * r.getMass() / distance;

                nLayout.dx += xDist * factor;
                nLayout.dy += yDist * factor;
                if (isThreeD) {
                    nLayout.dz += zDist * factor;
                }
            }
        }

        @Override
        public void apply(Node n, double g) {
            NodeData nData = n.getNodeData();
            ForceAtlas2LayoutData nLayout = nData.getLayoutData();

            // Get the distance
            double xDist = nData.x();
            double yDist = nData.y();
            double zDist = nData.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist);
            }


            if (distance > 0) {
                // NB: factor = force / distance
                double factor = coefficient * nLayout.mass * g / distance;

                nLayout.dx -= xDist * factor;
                nLayout.dy -= yDist * factor;
                if (isThreeD) {
                    nLayout.dz -= zDist * factor;
                }
            }
        }
    }

    private class strongGravity extends RepulsionForce {

        private double coefficient;
        private boolean isThreeD;

        public strongGravity(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;

        }

        @Override
        public void apply(Node n1, Node n2) {
            // Not Relevant
        }

        @Override
        public void apply(Node n, Region r) {
            // Not Relevant
        }

        @Override
        public void apply(Node n, double g) {
            NodeData nData = n.getNodeData();
            ForceAtlas2LayoutData nLayout = nData.getLayoutData();

            // Get the distance
            double xDist = nData.x();
            double yDist = nData.y();
            double zDist = nData.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist);
            }


            if (distance > 0) {
                // NB: factor = force / distance
                double factor = coefficient * nLayout.mass * g;

                nLayout.dx -= xDist * factor;
                nLayout.dy -= yDist * factor;
                if (isThreeD) {
                    nLayout.dz -= zDist * factor;
                }
            }
        }
    }

    /*
     * Attraction force: Linear
     */
    private class linAttraction extends AttractionForce {

        private double coefficient;
        private boolean isThreeD;

        public linAttraction(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;

        }

        @Override
        public void apply(Node n1, Node n2, double e) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();

            // NB: factor = force / distance
            double factor = -coefficient * e;

            n1Layout.dx += xDist * factor;
            n1Layout.dy += yDist * factor;
            if (isThreeD) {
                n1Layout.dz += zDist * factor;
            }
            n2Layout.dx -= xDist * factor;
            n2Layout.dy -= yDist * factor;
            if (isThreeD) {
                n2Layout.dz -= zDist * factor;
            }
        }
    }

    /*
     * Attraction force: Linear, distributed by mass (typically, degree)
     */
    private class linAttraction_massDistributed extends AttractionForce {

        private double coefficient;
        private boolean isThreeD;

        public linAttraction_massDistributed(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;

        }

        @Override
        public void apply(Node n1, Node n2, double e) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();

            // NB: factor = force / distance
            double factor = -coefficient * e / n1Layout.mass;

            n1Layout.dx += xDist * factor;
            n1Layout.dy += yDist * factor;
            if (isThreeD) {
                n1Layout.dz += zDist * factor;
            }
            n2Layout.dx -= xDist * factor;
            n2Layout.dy -= yDist * factor;
            if (isThreeD) {
                n2Layout.dz -= zDist * factor;
            }
        }
    }

    /*
     * Attraction force: Logarithmic
     */
    private class logAttraction extends AttractionForce {

        private double coefficient;
        private boolean isThreeD;

        public logAttraction(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;

        }

        @Override
        public void apply(Node n1, Node n2, double e) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist);
            }


            if (distance > 0) {

                // NB: factor = force / distance
                double factor = -coefficient * e * Math.log(1 + distance) / distance;

                n1Layout.dx += xDist * factor;
                n1Layout.dy += yDist * factor;
                if (isThreeD) {
                    n1Layout.dz += zDist * factor;
                }
                n2Layout.dx -= xDist * factor;
                n2Layout.dy -= yDist * factor;
                if (isThreeD) {
                    n2Layout.dz -= zDist * factor;
                }
            }
        }
    }

    /*
     * Attraction force: Linear, distributed by Degree
     */
    private class logAttraction_degreeDistributed extends AttractionForce {

        private double coefficient;
        private boolean isThreeD;

        public logAttraction_degreeDistributed(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;

        }

        @Override
        public void apply(Node n1, Node n2, double e) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();
            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist);
            }

            if (distance > 0) {

                // NB: factor = force / distance
                double factor = -coefficient * e * Math.log(1 + distance) / distance / n1Layout.mass;

                n1Layout.dx += xDist * factor;
                n1Layout.dy += yDist * factor;
                if (isThreeD) {
                    n1Layout.dz += zDist * factor;
                }
                n2Layout.dx -= xDist * factor;
                n2Layout.dy -= yDist * factor;
                if (isThreeD) {
                    n2Layout.dz -= zDist * factor;
                }
            }
        }
    }

    /*
     * Attraction force: Linear, with Anti-Collision
     */
    private class linAttraction_antiCollision extends AttractionForce {

        private double coefficient;
        private boolean isThreeD;

        public linAttraction_antiCollision(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;

        }

        @Override
        public void apply(Node n1, Node n2, double e) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist) - n1Data.getSize() - n2Data.getSize();
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist) - n1Data.getSize() - n2Data.getSize();
            }

            if (distance > 0) {
                // NB: factor = force / distance
                double factor = -coefficient * e;

                n1Layout.dx += xDist * factor;
                n1Layout.dy += yDist * factor;
                if (isThreeD) {
                    n1Layout.dz += zDist * factor;
                }
                n2Layout.dx -= xDist * factor;
                n2Layout.dy -= yDist * factor;
                if (isThreeD) {
                    n2Layout.dz -= zDist * factor;
                }
            }
        }
    }

    /*
     * Attraction force: Linear, distributed by Degree, with Anti-Collision
     */
    private class linAttraction_degreeDistributed_antiCollision extends AttractionForce {

        private double coefficient;
        private boolean isThreeD;

        public linAttraction_degreeDistributed_antiCollision(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;

        }

        @Override
        public void apply(Node n1, Node n2, double e) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist) - n1Data.getSize() - n2Data.getSize();
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist) - n1Data.getSize() - n2Data.getSize();
            }

            if (distance > 0) {
                // NB: factor = force / distance
                double factor = -coefficient * e / n1Layout.mass;

                n1Layout.dx += xDist * factor;
                n1Layout.dy += yDist * factor;
                if (isThreeD) {
                    n1Layout.dz += zDist * factor;
                }
                n2Layout.dx -= xDist * factor;
                n2Layout.dy -= yDist * factor;
                if (isThreeD) {
                    n2Layout.dz -= zDist * factor;
                }
            }
        }
    }

    /*
     * Attraction force: Logarithmic, with Anti-Collision
     */
    private class logAttraction_antiCollision extends AttractionForce {

        private double coefficient;
        private boolean isThreeD;

        public logAttraction_antiCollision(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;

        }

        @Override
        public void apply(Node n1, Node n2, double e) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist) - n1Data.getSize() - n2Data.getSize();
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist) - n1Data.getSize() - n2Data.getSize();
            }

            if (distance > 0) {

                // NB: factor = force / distance
                double factor = -coefficient * e * Math.log(1 + distance) / distance;

                n1Layout.dx += xDist * factor;
                n1Layout.dy += yDist * factor;
                if (isThreeD) {
                    n1Layout.dz += zDist * factor;
                }
                n2Layout.dx -= xDist * factor;
                n2Layout.dy -= yDist * factor;
                if (isThreeD) {
                    n2Layout.dz -= zDist * factor;
                }
            }
        }
    }

    /*
     * Attraction force: Linear, distributed by Degree, with Anti-Collision
     */
    private class logAttraction_degreeDistributed_antiCollision extends AttractionForce {

        private double coefficient;
        private boolean isThreeD;

        public logAttraction_degreeDistributed_antiCollision(double c, boolean is3D) {
            coefficient = c;
            isThreeD = is3D;
        }

        @Override
        public void apply(Node n1, Node n2, double e) {
            NodeData n1Data = n1.getNodeData();
            ForceAtlas2LayoutData n1Layout = n1Data.getLayoutData();
            NodeData n2Data = n2.getNodeData();
            ForceAtlas2LayoutData n2Layout = n2Data.getLayoutData();

            // Get the distance
            double xDist = n1Data.x() - n2Data.x();
            double yDist = n1Data.y() - n2Data.y();
            double zDist = n1Data.z() - n2Data.z();

            double distance;
            if (isThreeD) {
                distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist) - n1Data.getSize() - n2Data.getSize();
            } else {
                distance = Math.sqrt(xDist * xDist + yDist * yDist) - n1Data.getSize() - n2Data.getSize();
            }

            if (distance > 0) {

                // NB: factor = force / distance
                double factor = -coefficient * e * Math.log(1 + distance) / distance / n1Layout.mass;

                n1Layout.dx += xDist * factor;
                n1Layout.dy += yDist * factor;
                if (isThreeD) {
                    n1Layout.dz += zDist * factor;
                }

                n2Layout.dx -= xDist * factor;
                n2Layout.dy -= yDist * factor;
                if (isThreeD) {
                    n2Layout.dz -= zDist * factor;
                }
            }
        }
    }
}
