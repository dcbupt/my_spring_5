package org.springframework.context.expression;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class MineTests {

	@Test
	public void test1() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("end", "!");
		System.out.println(expression.getValue(context));
	}


	@Test
	public void testParserContext() {
		ExpressionParser parser = new SpelExpressionParser();
		ParserContext parserContext = new ParserContext() {
			@Override
			public boolean isTemplate() {
				return true;
			}

			@Override
			public String getExpressionPrefix() {
				return "#{";
			}

			@Override
			public String getExpressionSuffix() {
				return "}";
			}
		};
		String template = "#{'Hello '}#{'World!'}";
		Expression expression = parser.parseExpression(template, parserContext);
		System.out.println(expression.getValue());
	}


	@Test
	public void test6() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		User user = new User();
		Car car = new Car();
		car.setName("保时捷");
		user.setCar(car);
		factory.registerSingleton("user", user);

		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(new BeanFactoryResolver(factory));

		ExpressionParser parser = new SpelExpressionParser();
		User userBean = parser.parseExpression("@user").getValue(context, User.class);
		System.out.println(userBean);
		System.out.println(userBean == factory.getBean("user"));
	}

	public static class Car {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Car{" +
					"name='" + name + '\'' +
					'}';
		}
	}

	public static class User {
		private Car car;

		public Car getCar() {
			return car;
		}

		public void setCar(Car car) {
			this.car = car;
		}

		@Override
		public String toString() {
			return "User{" +
					"car=" + car +
					'}';
		}
	}
}
