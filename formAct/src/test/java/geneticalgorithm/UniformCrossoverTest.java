package geneticalgorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.solution.doublesolution.impl.DefaultDoubleSolution;
import org.uma.jmetal.util.bounds.Bounds;
import org.uma.jmetal.util.errorchecking.JMetalException;
import org.uma.jmetal.util.errorchecking.exception.InvalidConditionException;
import org.uma.jmetal.util.errorchecking.exception.NullParameterException;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import org.uma.jmetal.util.pseudorandom.RandomGenerator;

import pianoformativo.geneticalgorithm.UniformCrossover;

public class UniformCrossoverTest {
	
	private CrossoverOperator<DoubleSolution> crossover;
	
	private DoubleSolution solution0;
	private DoubleSolution solution1;
	private DoubleSolution solution2;
	
	@Before
	public void setUp() {
		
		int numberOfObjectives = 1;
		
		List<Double> lowerBounds = new ArrayList<>(Arrays.asList(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D));
		List<Double> upperBounds = new ArrayList<>(Arrays.asList(50.0D, 50.0D, 50.0D, 50.0D, 50.0D, 50.0D, 50.0D, 50.0D, 50.0D, 50.0D)); 
		
		List<Bounds<Double>> boundsList = new ArrayList<Bounds<Double>>();
		boundsList = IntStream.range(0, lowerBounds.size())
				.mapToObj(i -> Bounds.create(lowerBounds.get(i), upperBounds.get(i)))
				.collect(Collectors.toList());
		
		solution0 = new DefaultDoubleSolution(numberOfObjectives, boundsList);
		solution1 = new DefaultDoubleSolution(numberOfObjectives, boundsList);
		solution2 = new DefaultDoubleSolution(numberOfObjectives, boundsList);
		
	}
	
	@Test
	public void testConstructor() {
		
		crossover = new UniformCrossover<>(0.2D);
		assertNotNull(crossover);
		assertEquals(0.2D, crossover.getCrossoverProbability(), 0.0D);
		
		crossover = new UniformCrossover<>(0.4D, () -> JMetalRandom.getInstance().nextDouble());
		assertNotNull(crossover);
		assertEquals(0.4D, crossover.getCrossoverProbability(), 0.0D);
		
		crossover = new UniformCrossover<>(0.6D, () -> JMetalRandom.getInstance().nextDouble(), JMetalRandom.getInstance());
		assertNotNull(crossover);
		assertEquals(0.6D, crossover.getCrossoverProbability(), 0.0D);
		
	}
	
	@Test (expected = JMetalException.class)
	public void testConstructor1Exception() {
		
		crossover = new UniformCrossover<>(-0.2D);
		
	}
	
	@Test (expected = JMetalException.class)
	public void testConstructor2Exception() {
		
		crossover = new UniformCrossover<>(-5.5D, () -> JMetalRandom.getInstance().nextDouble());
		
	}
	
	@Test (expected = JMetalException.class)
	public void testConstructor3Exception() {
		
		crossover = new UniformCrossover<>(-10.0D, () -> JMetalRandom.getInstance().nextDouble(), JMetalRandom.getInstance());
		
	}
	
	@Test
	public void testGetSetCrossoverProbability() {
		
		crossover = new UniformCrossover<>(0.4D);
		((UniformCrossover<DoubleSolution>) crossover).setCrossoverProbability(0.8D);
		assertEquals(0.8D, crossover.getCrossoverProbability(), 0.0D);
		
	}
	
	@Test
	public void testGetCrossoverRandomGenerator() {
		
		UniformCrossover<DoubleSolution> crossover = new UniformCrossover<>(0.4D);
		assertNotNull(crossover.getCrossoverRandomGenerator());
		
	}
	
	@Test
	public void testGetJmetalRandomGenerator() {
		
		UniformCrossover<DoubleSolution> crossover = new UniformCrossover<>(0.4D);
		assertNotNull(crossover.getJmetalRandomGenerator());
		
	}
	
	@Test
	public void testExecute() {
		
		crossover = new UniformCrossover<>(0.9D);
		List<DoubleSolution> solutions = new ArrayList<>(Arrays.asList(solution0, solution1));
		List<DoubleSolution> resultSolutions = crossover.execute(solutions);
		assertNotNull(resultSolutions);
		
	}
	
	@Test (expected = NullParameterException.class)
	public void testExecuteException1() {
		
		crossover = new UniformCrossover<>(0.9D);
		List<DoubleSolution> solutions = null;
		crossover.execute(solutions);
		
	}
	
	@Test (expected = InvalidConditionException.class)
	public void testExecuteException2() {
		
		crossover = new UniformCrossover<>(0.9D);
		List<DoubleSolution> solutions = new ArrayList<>(Arrays.asList(solution0, solution1, solution2));
		crossover.execute(solutions);	
		
	}
	
	@Test
	public void testDoCrossoverNoCrossover() {
		
		double crossoverProbability = 0.5;
		RandomGenerator<Double> crossoverRandomGenerator = mock(RandomGenerator.class);
		
		crossover = new UniformCrossover<>(crossoverProbability, crossoverRandomGenerator);
		
		List<DoubleSolution> solutions = new ArrayList<>(Arrays.asList(solution0, solution1));
		
		Mockito.when(crossoverRandomGenerator.getRandomValue()).thenReturn(0.6);
		List<DoubleSolution> resultSolutions = crossover.execute(solutions);
		
		assertEquals(solutions.get(0).variables(), resultSolutions.get(0).variables()) ;
		assertEquals(solutions.get(1).variables(), resultSolutions.get(1).variables()) ;
	    
	}
	
	@Test
	public void testDoCrossoverYesCrossover() {
		
		double crossoverProbability = 0.8;
		RandomGenerator<Double> crossoverRandomGenerator = mock(RandomGenerator.class);
		JMetalRandom jmetalRandomGenerator =  mock(JMetalRandom.class);
		
		crossover = new UniformCrossover<>
				(crossoverProbability, crossoverRandomGenerator, jmetalRandomGenerator);
		
		List<DoubleSolution> solutions = new ArrayList<>(Arrays.asList(solution0, solution1));
		System.out.println(solutions.get(0).variables());
		System.out.println(solutions.get(1).variables());
		
		Mockito.when(crossoverRandomGenerator.getRandomValue()).thenReturn(0.5);
		Mockito.when(jmetalRandomGenerator.nextInt(0, 1)).thenReturn(0, 1, 0, 0, 1, 0, 1, 0, 0, 1);
		List<DoubleSolution> resultSolutions = crossover.execute(solutions);
		System.out.println(resultSolutions.get(0).variables());
		System.out.println(resultSolutions.get(1).variables());
		
		assertNotEquals(solutions.get(0).variables(), resultSolutions.get(0).variables()) ;
		assertNotEquals(solutions.get(1).variables(), resultSolutions.get(1).variables()) ;
		
		for (int i = 0; i < solutions.size(); i++) {
			for (int j = 0; j < solutions.get(0).variables().size(); j++) {
				if (j == 0 || j == 2 || j == 3 || j == 5 || j == 7 || j == 8) {
					assertEquals(solutions.get(i).variables().get(j), resultSolutions.get(i).variables().get(j));
				}
				else {
					assertNotEquals(solutions.get(i).variables().get(j), resultSolutions.get(i).variables().get(j));					
				}
			}
		}
		
	}
	
	@Test
	public void testGetNumberOfRequiredParents() {
		
		crossover = new UniformCrossover<>(0.8D);
		assertEquals(2, crossover.getNumberOfRequiredParents());
		
	}
	
	@Test
	public void testGetNumberOfGeneratedChildren() {
		
		crossover = new UniformCrossover<>(0.4D);
		assertEquals(2, crossover.getNumberOfGeneratedChildren());
		
	}
	
}









