/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package demo.jaxrs.server;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mysql.fabric.xmlrpc.base.Array;

@XmlRootElement(name = "Customer")
public class Customer {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {

		List<String> list = Arrays.asList("jack1", "tom1", "sam2", "funci1", "luciak", "manos", "123pa");

		// Collections.sort(list, new Comparator<String>() {
		// @Override
		// public int compare(String o1, String o2) {
		// // TODO Auto-generated method stub
		// return o1.compareTo(o2);
		// }
		//
		// });

		// Collections.sort(list, (p1,p2) -> p2.compareTo(p1));

		//list.stream().sorted((p1, p2) -> p1.compareTo(p2)).map(n -> n + "stream handler").forEach(System.out::println);

		for (String name : list) {
			System.out.println(name);
		}
		

	}
}
