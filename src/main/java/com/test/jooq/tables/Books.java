/*
 * This file is generated by jOOQ.
 */
package com.test.jooq.tables;


import com.test.jooq.Keys;
import com.test.jooq.Public;
import com.test.jooq.tables.Authors.AuthorsPath;
import com.test.jooq.tables.records.BooksRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.SelectField;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Books extends TableImpl<BooksRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.books</code>
     */
    public static final Books BOOKS = new Books();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BooksRecord> getRecordType() {
        return BooksRecord.class;
    }

    /**
     * The column <code>public.books.id</code>.
     */
    public final TableField<BooksRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.books.title</code>.
     */
    public final TableField<BooksRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.books.isbn</code>.
     */
    public final TableField<BooksRecord, String> ISBN = createField(DSL.name("isbn"), SQLDataType.VARCHAR(13).nullable(false), this, "");

    /**
     * The column <code>public.books.author_id</code>.
     */
    public final TableField<BooksRecord, Long> AUTHOR_ID = createField(DSL.name("author_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.books.created_at</code>.
     */
    public final TableField<BooksRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.books.updated_at</code>.
     */
    public final TableField<BooksRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("updated_at"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.books.deleted_at</code>.
     */
    public final TableField<BooksRecord, LocalDateTime> DELETED_AT = createField(DSL.name("deleted_at"), SQLDataType.LOCALDATETIME(6), this, "");

    private Books(Name alias, Table<BooksRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Books(Name alias, Table<BooksRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.books</code> table reference
     */
    public Books(String alias) {
        this(DSL.name(alias), BOOKS);
    }

    /**
     * Create an aliased <code>public.books</code> table reference
     */
    public Books(Name alias) {
        this(alias, BOOKS);
    }

    /**
     * Create a <code>public.books</code> table reference
     */
    public Books() {
        this(DSL.name("books"), null);
    }

    public <O extends Record> Books(Table<O> path, ForeignKey<O, BooksRecord> childPath, InverseForeignKey<O, BooksRecord> parentPath) {
        super(path, childPath, parentPath, BOOKS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class BooksPath extends Books implements Path<BooksRecord> {
        public <O extends Record> BooksPath(Table<O> path, ForeignKey<O, BooksRecord> childPath, InverseForeignKey<O, BooksRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private BooksPath(Name alias, Table<BooksRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public BooksPath as(String alias) {
            return new BooksPath(DSL.name(alias), this);
        }

        @Override
        public BooksPath as(Name alias) {
            return new BooksPath(alias, this);
        }

        @Override
        public BooksPath as(Table<?> alias) {
            return new BooksPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<BooksRecord, Long> getIdentity() {
        return (Identity<BooksRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<BooksRecord> getPrimaryKey() {
        return Keys.BOOKS_PKEY;
    }

    @Override
    public List<UniqueKey<BooksRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.BOOKS_ISBN_KEY);
    }

    @Override
    public List<ForeignKey<BooksRecord, ?>> getReferences() {
        return Arrays.asList(Keys.BOOKS__BOOKS_AUTHOR_ID_FKEY);
    }

    private transient AuthorsPath _authors;

    /**
     * Get the implicit join path to the <code>public.authors</code> table.
     */
    public AuthorsPath authors() {
        if (_authors == null)
            _authors = new AuthorsPath(this, Keys.BOOKS__BOOKS_AUTHOR_ID_FKEY, null);

        return _authors;
    }

    @Override
    public Books as(String alias) {
        return new Books(DSL.name(alias), this);
    }

    @Override
    public Books as(Name alias) {
        return new Books(alias, this);
    }

    @Override
    public Books as(Table<?> alias) {
        return new Books(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Books rename(String name) {
        return new Books(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Books rename(Name name) {
        return new Books(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Books rename(Table<?> name) {
        return new Books(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Books where(Condition condition) {
        return new Books(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Books where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Books where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Books where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Books where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Books where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Books where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Books where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Books whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Books whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, String, String, Long, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super Long, ? super String, ? super String, ? super Long, ? super LocalDateTime, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super Long, ? super String, ? super String, ? super Long, ? super LocalDateTime, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
