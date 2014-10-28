package org.theeuropeanlibrary.hera.rest.administration.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.theeuropeanlibrary.maia.common.definitions.Dataset;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

/**
 * Result slice for methods that would return possibly huge collections of objects that must be divided into paged to
 * handle them.
 *
 * @param <T> Class of returned objects.
 */
@XmlRootElement
@XmlSeeAlso({Provider.class, Dataset.class}) // references to all classes that might be used as generics parameters
public class ResultSlice<T> {

	/**
	 * Reference to next slice of result.
	 */
	private String nextSlice;

	/**
	 * List of results in this slice.
	 */
	private List<T> results = new ArrayList<T>();


	/**
	 * Creates a new instance of this class.
	 * @param nextSlice
	 * @param results
	 */
	public ResultSlice(String nextSlice, List<T> results) {
		this.nextSlice = nextSlice;
		this.results = Collections.unmodifiableList(results);
	}


	/**
	 * Creates a new instance of this class.
	 */
	public ResultSlice() {
	}


	public String getNextSlice() {
		return nextSlice;
	}


	public List<T> getResults() {
		return results;
	}


	public void setNextSlice(String nextSlice) {
		this.nextSlice = nextSlice;
	}


	public void setResults(List<T> results) {
		this.results = results;
	}

}
