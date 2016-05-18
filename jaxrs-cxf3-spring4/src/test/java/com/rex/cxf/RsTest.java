package com.rex.cxf;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.testutil.common.AbstractBusClientServerTestBase;
import org.apache.cxf.transport.local.LocalTransportFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.rex.cxf.service.FruitService;
import com.rex.cxf.service.IFruitService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RsTest extends AbstractBusClientServerTestBase {
	private static Server localServer;

	static IFruitService localProxy;

	@BeforeClass
	public static void setUp() {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		// sf.setResourceClasses(IFruitService.class);
		// sf.setResourceProvider(IFruitService.class, new
		// SingletonResourceProvider(new FruitService(), true));
		sf.setServiceBean(new FruitService());
		sf.setProvider(new JacksonJsonProvider());

		// sf.setTransportId(LocalTransportFactory.TRANSPORT_ID);
		sf.setAddress("local://fruit");
		localServer = sf.create();

		localProxy = prepareClient();
	}

	private static IFruitService prepareClient() {
		JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
		// bean.setTransportId(LocalTransportFactory.TRANSPORT_ID);
		bean.setAddress("local://fruit");
		bean.setServiceClass(IFruitService.class);
		// IFruitService localProxy = (IFruitService) bean.create();
		return (IFruitService) bean.create();
	}

	@Test
	public void testProxyPipedDispatchGet() throws Exception {

		int ct = localProxy.getFruitCount("apple");
		assertEquals(100, ct);
	}

	@Test
	public void testAddFruit() throws Exception {
		localProxy.addFruit("xx", "333",null);
		int ct = localProxy.getFruitCount("xx");
		assertEquals(333, ct);
	}
	
	@Test
	public void updateFruit() {
		localProxy.updateFruit("apple", "1234");
		int ct = localProxy.getFruitCount("apple");
		assertEquals(1234, ct);
	}
	

	@AfterClass
	public static void tearDown() {
		if (localServer != null) {
			localServer.stop();
			localServer.destroy();
		}
	}

}
