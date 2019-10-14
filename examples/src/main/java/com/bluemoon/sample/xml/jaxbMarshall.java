package com.bluemoon.sample.xml;

import com.bluemoon.sample.xml.model.Customer;
import com.bluemoon.sample.xml.model.Order;
import com.bluemoon.sample.xml.model.PhoneNumber;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class jaxbMarshall {

	public static void main (String[] args) throws JAXBException {
		// example 1
		System.out.println("============================================================");
		System.out.println("====================== example 1 ===========================");
		System.out.println("============================================================");
		Order order = new Order();
		order.setPrice("10");
		order.setTradeNo("123");
		order.setTradeSuccess("success");

		StringWriter stringWriter = new StringWriter();
		JAXB.marshal(order, stringWriter);
		String xmlString = stringWriter.toString();
		System.out.println(xmlString);

		// example 2
		System.out.println("============================================================");
		System.out.println("====================== example 2 ===========================");
		System.out.println("============================================================");
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Order.class);
			Marshaller marshaller = context.createMarshaller();
			stringWriter = new StringWriter();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.marshal(order, stringWriter);
			String xmlString2 = stringWriter.toString();
			System.out.println(xmlString2);
			try {
				System.out.println(URLEncoder.encode(xmlString2, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (JAXBException e) {
		}

		// example 3
		System.out.println();
		System.out.println("============================================================");
		System.out.println("====================== example 3 ===========================");
		System.out.println("============================================================");
		JAXBContext jc = JAXBContext.newInstance(Customer.class);

		Customer customer = new Customer();
		customer.setFirstName("Jane");
		customer.setLastName("Doe");

		PhoneNumber workPhone = new PhoneNumber();
		workPhone.setType("work");
		workPhone.setNumber("555-1111");
		customer.getPhoneNumbers().add(workPhone);

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		JAXBElement<Customer> rootElement = new JAXBElement<Customer>(new QName("customer"), Customer.class, customer);
		marshaller.marshal(rootElement, System.out);
	}
}
