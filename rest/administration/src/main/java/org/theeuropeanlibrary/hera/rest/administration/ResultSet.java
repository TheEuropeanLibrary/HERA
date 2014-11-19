package org.theeuropeanlibrary.hera.rest.administration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.theeuropeanlibrary.maia.common.definitions.Dataset;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

/**
 * Container for a list of results.
 * 
 * @param <T> Class of returned objects.
 */
@XmlRootElement
@XmlSeeAlso({Provider.class, Dataset.class}) // references to all classes that might be used as generics parameters
public class ResultSet<T> {

	/**
	 * List of results
	 */
	private List<T> results = new ArrayList<T>();

	/**
	 * Creates a new instance of this class.
	 * @param results
	 */
	public ResultSet(List<T> results) {
		this.results = Collections.unmodifiableList(results);
	}
	
	public ResultSet() {
	}

	public List<T> getResults() {
		return results;
	}


	public void setResults(List<T> results) {
		this.results = results;
	}
}
