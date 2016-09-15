package sorcer.provider.adder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.provider.adder.impl.AdderImpl;
import sorcer.service.*;
import sorcer.service.modeling.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static sorcer.co.operator.*;
import static sorcer.eo.operator.*;
import static sorcer.eo.operator.get;
import static sorcer.eo.operator.value;
import static sorcer.mo.operator.response;
import static sorcer.po.operator.ent;

/**
 * @author Mike Sobolewski
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/service")
public class LocalMograms {
	private final static Logger logger = LoggerFactory.getLogger(LocalMograms.class);

	@Test
	public void exertTask() throws Exception {

		Task t5 = task("t5", sig("add", AdderImpl.class),
				cxt("add", inVal("arg/x1", 20.0), inVal("arg/x2", 80.0)));

		Task out = exert(t5);
		Context cxt = context(out);
		logger.info("out context: " + cxt);
		logger.info("context @ arg/x1: " + get(cxt, "arg/x1"));
		logger.info("context @ arg/x2: " + value(cxt, "arg/x2"));
		logger.info("context @ calculated/provider: " + value(cxt, "calculated/provider"));
		logger.info("context @ result/eval: " + value(cxt, "eval/result"));
		// same as "eval/result"
		logger.info("context @ result/eval: " + value(cxt, Adder.RESULT_PATH));

		// get a single context argument
		assertEquals(100.0, value(cxt, "eval/result"));

		// get the subcontext output from the context
		assertTrue(context(ent("arg/x1", 20.0), ent("eval/result", 100.0)).equals(
				value(cxt, outPaths("arg/x1", "eval/result"))));
	}

	@Test
	public void evaluateTask() throws SignatureException, ExertionException, ContextException {

		Task t5 = task("t5", sig("add", AdderImpl.class),
				cxt("add", inVal("arg/x1", 20.0), inVal("arg/x2", 80.0), result("result/y")));

		// get the result eval
		assertEquals(100.0, eval(t5));

		// get the output subcontext from the context
		assertTrue(context(ent("arg/x1", 20.0), ent("result/z", 100.0)).equals(
				eval(t5, result("result/z", outPaths("arg/x1", "result/z")))));
	}

	@Test
	public void filterTaskContext() throws SignatureException, ExertionException, ContextException {

		Task t5 = task("t5", sig("add", AdderImpl.class),
				cxt("add", inVal("arg/x1", 20.0), inVal("arg/x2", 80.0), outVal("out/val"),
						outPaths("out/val", "calculated/provider")));

		// get the subcontext output from the exertion
		assertTrue(context(ent("calculated/provider", AdderImpl.class.getName()),
				ent("out/val", 100.0)).equals(eval(t5)));
	}

	@Test
	public void filterSingleOutPathContext() throws SignatureException, ExertionException, ContextException {

		Task t5 = task("t5", sig("add", AdderImpl.class),
				cxt("add", inVal("arg/x1", 20.0), inVal("arg/x2", 80.0), outVal("out/val"),
						outPaths("out/val")));

		// get the result eval
		assertEquals(100.0, eval(t5));
	}

	@Test
	public void evalauteLocalModel() throws Exception {

		// three entry model
		Model mod = model(inVal("arg/x1", 10.00), inVal("arg/x2", 90.00),
				ent(sig("add", AdderImpl.class, result("result/y", inPaths("arg/x1", "arg/x2")))),
				sorcer.mo.operator.response("add", "arg/x1", "arg/x2"));

		Context out = response(mod);
		assertTrue(get(out, "add").equals(100.0));

		assertTrue(get(mod, "result/y").equals(100.0));
	}

}
	
	
