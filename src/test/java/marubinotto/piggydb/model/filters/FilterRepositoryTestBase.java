package marubinotto.piggydb.model.filters;

import java.util.List;

import marubinotto.piggydb.external.jdbc.h2.InMemoryDatabase;
import marubinotto.piggydb.model.Filter;
import marubinotto.piggydb.model.FilterRepository;
import marubinotto.piggydb.model.RepositoryTestBase;
import marubinotto.piggydb.model.repository.FilterRepositoryRI;

import org.junit.runners.Parameterized.Parameters;

public abstract class FilterRepositoryTestBase 
extends RepositoryTestBase<FilterRepository> {
	
	public FilterRepositoryTestBase(
			RepositoryFactory<FilterRepository> factory) {
		super(factory);
	}
	
	@Parameters
	public static List<Object[]> factories() {
		return toParameters(
			// RI
			new RepositoryFactory<FilterRepository>() {
				public FilterRepository create() throws Exception {
					return new FilterRepositoryRI();
				}
			},
			// Database
			new RepositoryFactory<FilterRepository>() {
				public FilterRepository create() throws Exception {
					return new InMemoryDatabase().getFilterRepository();
				}
			}
		);
	}
	
	public Filter newFilter(String name) {
		Filter filter = this.object.newInstance(getPlainUser());
		filter.setNameByUser(name, getPlainUser());
		return filter;
	}
}
