/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math3.analysis.interpolation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for the piecewise bicubic function.
 */
public final class PiecewiseBicubicSplineInterpolatingFunctionTest {
    /**
     * Test preconditions.
     */
    @Test
    public void testPreconditions() {
        double[] xval = new double[] { 3, 4, 5, 6.5, 7.5 };
        double[] yval = new double[] { -4, -3, -1, 2.5, 3.5 };
        double[][] zval = new double[xval.length][yval.length];

        @SuppressWarnings("unused")
        PiecewiseBicubicSplineInterpolatingFunction bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval, yval, zval );

        try {
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( null, yval, zval );
            Assert.fail( "Failed to detect x null pointer" );
        } catch ( NullArgumentException iae ) {
            // Expected.
        }

        try {
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval, null, zval );
            Assert.fail( "Failed to detect y null pointer" );
        } catch ( NullArgumentException iae ) {
            // Expected.
        }

        try {
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval, yval, null );
            Assert.fail( "Failed to detect z null pointer" );
        } catch ( NullArgumentException iae ) {
            // Expected.
        }

        try {
            double xval1[] = { 0.0, 1.0, 2.0, 3.0 };
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval1, yval, zval );
            Assert.fail( "Failed to detect insufficient x data" );
        } catch ( InsufficientDataException iae ) {
            // Expected.
        }

        try {
            double yval1[] = { 0.0, 1.0, 2.0, 3.0 };
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval, yval1, zval );
            Assert.fail( "Failed to detect insufficient y data" );
        } catch ( InsufficientDataException iae ) {
            // Expected.
        }

        try {
            double zval1[][] = new double[4][4];
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval, yval, zval1 );
            Assert.fail( "Failed to detect insufficient z data" );
        } catch ( InsufficientDataException iae ) {
            // Expected.
        }

        try {
            double xval1[] = { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval1, yval, zval );
            Assert.fail( "Failed to detect data set array with different sizes." );
        } catch ( DimensionMismatchException iae ) {
            // Expected.
        }

        try {
            double yval1[] = { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval, yval1, zval );
            Assert.fail( "Failed to detect data set array with different sizes." );
        } catch ( DimensionMismatchException iae ) {
            // Expected.
        }

        // X values not sorted.
        try {
            double xval1[] = { 0.0, 1.0, 0.5, 7.0, 3.5 };
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval1, yval, zval );
            Assert.fail( "Failed to detect unsorted x arguments." );
        } catch ( NonMonotonicSequenceException iae ) {
            // Expected.
        }

        // Y values not sorted.
        try {
            double yval1[] = { 0.0, 1.0, 1.5, 0.0, 3.0 };
            bcf = new PiecewiseBicubicSplineInterpolatingFunction( xval, yval1, zval );
            Assert.fail( "Failed to detect unsorted y arguments." );
        } catch ( NonMonotonicSequenceException iae ) {
            // Expected.
        }
    }

    /**
     * Interpolating a plane.
     * <p>
     * z = 2 x - 3 y + 5
     */
    @Test
    public void testInterpolatePlane() {
        final int numberOfElements = 10;
        final double minimumX = -10;
        final double maximumX = 10;
        final double minimumY = -10;
        final double maximumY = 10;
        final int numberOfSamples = 100;
        final double interpolationTolerance = 7e-15;
        final double maxTolerance = 6e-14;

        // Function values
        BivariateFunction f = new BivariateFunction() {
                public double value( double x, double y ) {
                    return 2 * x - 3 * y + 5;
                }
            };

        testInterpolation( minimumX, maximumX, minimumY, maximumY, numberOfElements, numberOfSamples, f,
                           interpolationTolerance, maxTolerance );
    }

    /**
     * Interpolating a paraboloid.
     * <p>
     * z = 2 x<sup>2</sup> - 3 y<sup>2</sup> + 4 x y - 5
     */
    @Test
    public void testInterpolationParabaloid() {
        final int numberOfElements = 10;
        final double minimumX = -10;
        final double maximumX = 10;
        final double minimumY = -10;
        final double maximumY = 10;
        final int numberOfSamples = 100;
        final double interpolationTolerance = 2e-14;
        final double maxTolerance = 6e-14;

        // Function values
        BivariateFunction f = new BivariateFunction() {
                public double value( double x, double y ) {
                    return 2 * x * x - 3 * y * y + 4 * x * y - 5;
                }
            };

        testInterpolation( minimumX, maximumX, minimumY, maximumY, numberOfElements, numberOfSamples, f,
                           interpolationTolerance, maxTolerance );
    }

    private void testInterpolation( double minimumX, double maximumX, double minimumY, double maximumY,
                                    int numberOfElements, int numberOfSamples, BivariateFunction f, double tolerance,
                                    double maxTolerance ) {
        double expected;
        double actual;
        double currentX;
        double currentY;
        final double deltaX = ( maximumX - minimumX ) / ( (double) numberOfElements );
        final double deltaY = ( maximumY - minimumY ) / ( (double) numberOfElements );
        double xValues[] = new double[numberOfElements];
        double yValues[] = new double[numberOfElements];
        double zValues[][] = new double[numberOfElements][numberOfElements];

        for ( int i = 0; i < numberOfElements; i++ ) {
            xValues[i] = minimumX + deltaX * (double) i;
            for ( int j = 0; j < numberOfElements; j++ ) {
                yValues[j] = minimumY + deltaY * (double) j;
                zValues[i][j] = f.value( xValues[i], yValues[j] );
            }
        }

        BivariateFunction interpolation = new PiecewiseBicubicSplineInterpolatingFunction( xValues, yValues, zValues );

        for ( int i = 0; i < numberOfElements; i++ ) {
            currentX = xValues[i];
            for ( int j = 0; j < numberOfElements; j++ ) {
                currentY = yValues[j];
                expected = f.value( currentX, currentY );
                actual = interpolation.value( currentX, currentY );
                assertTrue( Precision.equals( expected, actual ) );
            }
        }

        final RandomGenerator rng = new Well19937c( 1234567L ); // "tol" depends on the seed.
        final UniformRealDistribution distX = new UniformRealDistribution( rng, xValues[0], xValues[xValues.length - 1] );
        final UniformRealDistribution distY = new UniformRealDistribution( rng, yValues[0], yValues[yValues.length - 1] );

        double sumError = 0;
        for ( int i = 0; i < numberOfSamples; i++ ) {
            currentX = distX.sample();
            currentY = distY.sample();
            expected = f.value( currentX, currentY );
            actual = interpolation.value( currentX, currentY );
            sumError += FastMath.abs( actual - expected );
            assertEquals( expected, actual, maxTolerance );
        }

        assertEquals( 0.0, ( sumError / (double) numberOfSamples ), tolerance );
    }
}
