import tester.Tester;

// Represents a predicate object taking in a T
interface IPred<T> {
	// Applies a predicate on the given T
	boolean apply(T t);
}

// Represents a function object going from a T to a U
interface IFunc<T, U> {
	// Applies an operation to the given T that results in a U
	U apply(T t);
}

// Represents a list of T
interface IList<T> {
	// Filters this list down to only Ts that match the given predicate object
	IList<T> filter(IPred<T> f);

	// Maps the given function object over every T in this list
	<U> IList<U> map(IFunc<T, U> f);

	// Appends that list onto this list
	IList<T> append(IList<T> that);

	// Accepts a IListVisitor and calls f.visit(this)
	<U> U accept(IListVisitor<T, U> f);
}

// Represents an empty list of T
class MtList<T> implements IList<T> {

MtList() { /*Represents an empty list*/ }

/*
TEMPLATE:
Methods:
... this.filter(IPred<T>) ...               -- IList<T>
... this.map(IFunc<T, U>) ...               -- IList<U>
... this.append(IList<T>) ...               -- IList<T>
... this.accept(IListVisitor<T, U>) ...     -- U
*/

// Filters this empty list down to only Ts that match the given predicate object
public IList<T> filter(IPred<T> f) {
	/*
	TEMPLATE: Everything in MtList<T>, plus
	Methods on Parameters:
	... f.apply(T) ...      -- boolean
	*/
	return this;
}

// Maps the given function object over every T in this empty list
public <U> IList<U> map(IFunc<T, U> f) {
	/*
	TEMPLATE: Everything in MtList<T>, plus
	Methods on parameters:
	... f.apply(T) ...      U
	*/
	return new MtList<U>();
}

// Appends that list onto this empty list
public IList<T> append(IList<T> that) {
	/*
	TEMPLATE:Everything in MtList<T>, plus
	Methods on parameters:
	... that.filter(IPred<T>) ...               -- IList<T>
	... that.map(IFunc<T, U>) ...               -- IList<U>
	... that.append(IList<T>) ...               -- IList<T>
	... that.accept(IListVisitor<T, U>) ...     -- U
	*/

	return that;
}

// Accepts a IListVisitor and calls f.visit(this)
public <U> U accept(IListVisitor<T, U> f) {
	/*
	TEMPLATE: Everything in MtList<T>, plus
	Methods on Parameters:
	... f.visit(ConsList<T>) ...      -- U
	... f.visit(MtList<T>) ...        -- U
	... f.apply(IList<T>) ...         -- IList<T>
	*/
	return f.visit(this);
}
}

// Represents a non-empty list of T
class ConsList<T> implements IList<T> {
	T first;
	IList<T> rest;

	ConsList(T first, IList<T> rest) {
		this.first = first;
		this.rest = rest;
	}
	/*
	TEMPLATE:
	Fields:
	... this.first ...                          -- T
	... this.rest ...                           -- IList<T>
	Methods:
	... this.filter(IPred<T>) ...               -- IList<T>
	... this.map(IFunc<T, U>) ...               -- IList<U>
	... this.append(IList<T>) ...               -- IList<T>
	... this.accept(IListVisitor<T, U>) ...     -- U
	Methods on Fields:
	... this.rest.filter(IPred<T>) ...               -- IList<T>
	... this.rest.map(IFunc<T, U>) ...               -- IList<U>
	... this.rest.append(IList<T>) ...               -- IList<T>
	... this.rest.accept(IListVisitor<T, U>) ...     -- U
	*/

	// Filters this list down to only Ts that match the given predicate object
	public IList<T> filter(IPred<T> f) {
		/*
		TEMPLATE: Everything in ConsList<T>, plus
		Methods on Parameters:
		... f.apply(T) ...      -- boolean
		*/
		if (f.apply(this.first)) {
			return new ConsList<T>(this.first, this.rest.filter(f));
		}
		else {
			return this.rest.filter(f);
		}
	}

	// Maps the given function object over every T in this list
	public <U> IList<U> map(IFunc<T, U> f) {
		/*
		TEMPLATE: Everything in ConsList<T>, plus
		Methods on parameters:
		... f.apply(T) ...      U
		*/
		return new ConsList<U>(f.apply(this.first), this.rest.map(f));
	}

	// Appends that list onto this list
	public IList<T> append(IList<T> other) {
		/*
		TEMPLATE:Everything in ConsList<T>, plus
		Methods on parameters:
		... other.filter(IPred<T>) ...               -- IList<T>
		... other.map(IFunc<T, U>) ...               -- IList<U>
		... other.append(IList<T>) ...               -- IList<T>
		... other.accept(IListVisitor<T, U>) ...     -- U
		*/
		return new ConsList<T>(this.first, this.rest.append(other));
	}

	// Accepts a IListVisitor and calls f.visit(this)
	public <U> U accept(IListVisitor<T, U> f) {
		/*
		TEMPLATE: Everything in ConsList<T>, plus
		Methods on Parameters:
		... f.visit(ConsList<T>) ...      -- U
		... f.visit(MtList<T>) ...        -- U
		... f.apply(IList<T>) ...         -- IList<T>
		*/
		return f.visit(this);
	}
}

// A MapVisitor object takes a function object (IPred) and when applied to a list maps
// that function object over the given IList<T>
class MapVisitor<T, U> implements IListVisitor<T, IList<U>> {
	IFunc<T, U> func;

	MapVisitor(IFunc<T, U> func) {
		this.func = func;
	}

	/*
	TEMPLATE:
	Fields
	... this.func ...     -- IFunc<T, U>
	Methods:
	... this.visit(ConsList<T>) ...     -- IList<U>
	... this.visit(MtList<T>) ...       -- IList<U>
	... this.apply(IList<T>) ...        -- IList<U>
	Methods on Fields:
	... func.apply(T) ...               -- U
	*/

	// Accepts the non-empty list that visits this visitor and map this.func over it
	public IList<U> visit(ConsList<T> cons) {
		/*
		TEMPLATE: Everything in MapVisitor<T, U>, plus
		Fields of Parameters:
		... cons.first ...      -- T
		... cons.rest ...       -- IList<T>
		Methods on parameters:
		... cons.filter(IPred<T>) ...               -- IList<T>
		... cons.map(IFunc<T, U>) ...               -- IList<U>
		... cons.append(IList<T>) ...               -- IList<T>
		... cons.accept(IListVisitor<T, U>) ...     -- U
		*/
		return new ConsList<U>(this.func.apply(cons.first), cons.rest.accept(this));
	}

	// Accepts the empty list and maps this.func over every element in the empty list
	public IList<U> visit(MtList<T> list) {
		/*
		TEMPLATE: Everything in MapVisitor<T, U>, plus
		Methods on parameters:
		... list.filter(IPred<T>) ...               -- IList<T>
		... list.map(IFunc<T, U>) ...               -- IList<U>
		... list.append(IList<T>) ...               -- IList<T>
		... list.accept(IListVisitor<T, U>) ...     -- U
		*/
		return new MtList<U>();
	}

	// Applies this visitor to the given list of T
	public IList<U> apply(IList<T> list) {
		/*
		TEMPLATE: Everything in MapVisitor<T, U>, plus
		Methods on Parameters:
		... list.filter(IPred<T>) ...               -- IList<T>
		... list.map(IFunc<T, U>) ...               -- IList<U>
		... list.append(IList<T>) ...               -- IList<T>
		... list.accept(IListVisitor<T, U>) ...     -- U
		*/
		return list.accept(this);
	}
}

// A MapVisitor object takes a function object (IPred) and when applied to a list filters
// that list to Ts that match the function object it was initialized with
class FilterVisitor<T> implements IListVisitor<T, IList<T>> {
	IPred<T> func;

	FilterVisitor(IPred<T> func) {
		this.func = func;
	}
	/*
	TEMPLATE:
	Fields:
	... this.func ...     -- IPred<T>
	Methods:
	... this.visit(ConsList<T>) ...     -- IList<U>
	... this.visit(MtList<T>) ...       -- IList<U>
	... this.apply(IList<T>) ...        -- IList<U>
	Methods on fields:
	... this.func.apply(T) ...      -- boolean
	*/

	// Accepts the non-empty list that visits this visitor and filter it to Ts that match this.func
	public IList<T> visit(ConsList<T> cons) {
		/*
		TEMPLATE: Everything in FilterVisitor<T>, plus
		Fields of Parameters:
		... cons.first ...      -- T
		... cons.rest ...       -- IList<T>
		Methods on parameters:
		... cons.filter(IPred<T>) ...               -- IList<T>
		... cons.map(IFunc<T, U>) ...               -- IList<U>
		... cons.append(IList<T>) ...               -- IList<T>
		... cons.accept(IListVisitor<T, U>) ...     -- U
		*/
		if (this.func.apply(cons.first)) {
			return new ConsList<T>(cons.first, cons.rest.accept(this));
		}
		else {
			return cons.rest.accept(this);
		}
	}

	// Accepts the empty list that visits this visitor and filter it to Ts that match this.func
	public IList<T> visit(MtList<T> list) {
		/*
		TEMPLATE: Everything in FilterVisitor<T>, plus
		Methods on parameters:
		... list.filter(IPred<T>) ...               -- IList<T>
		... list.map(IFunc<T, U>) ...               -- IList<U>
		... list.append(IList<T>) ...               -- IList<T>
		... list.accept(IListVisitor<T, U>) ...     -- U
		*/
		return list;
	}

	// Applies this visitor to the given list of T
	public IList<T> apply(IList<T> list) {
		/*
		TEMPLATE: Everything in FilterVisitor<T>, plus
		Methods on Parameters:
		... list.filter(IPred<T>) ...               -- IList<T>
		... list.map(IFunc<T, U>) ...               -- IList<U>
		... list.append(IList<T>) ...               -- IList<T>
		... list.accept(IListVisitor<T, U>) ...     -- U
		*/
		return list.accept(this);
	}
}

// Represents a visitor for ILists of T
interface IListVisitor<T, U> extends IFunc<IList<T>, U> {
	// Allows a ConsList<T> to visit this visitor
	U visit(ConsList<T> c);

	// Allows a MtList<T> to visit this visitor
	U visit(MtList<T> e);
}