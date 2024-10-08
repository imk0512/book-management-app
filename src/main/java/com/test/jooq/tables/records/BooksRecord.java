/*
 * This file is generated by jOOQ.
 */
package com.test.jooq.tables.records;


import com.test.jooq.tables.Books;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BooksRecord extends UpdatableRecordImpl<BooksRecord> implements Record7<Long, String, String, Long, LocalDateTime, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.books.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.books.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.books.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.books.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.books.isbn</code>.
     */
    public void setIsbn(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.books.isbn</code>.
     */
    public String getIsbn() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.books.author_id</code>.
     */
    public void setAuthorId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.books.author_id</code>.
     */
    public Long getAuthorId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>public.books.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.books.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>public.books.updated_at</code>.
     */
    public void setUpdatedAt(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.books.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>public.books.deleted_at</code>.
     */
    public void setDeletedAt(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.books.deleted_at</code>.
     */
    public LocalDateTime getDeletedAt() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, String, String, Long, LocalDateTime, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Long, String, String, Long, LocalDateTime, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Books.BOOKS.ID;
    }

    @Override
    public Field<String> field2() {
        return Books.BOOKS.TITLE;
    }

    @Override
    public Field<String> field3() {
        return Books.BOOKS.ISBN;
    }

    @Override
    public Field<Long> field4() {
        return Books.BOOKS.AUTHOR_ID;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return Books.BOOKS.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return Books.BOOKS.UPDATED_AT;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return Books.BOOKS.DELETED_AT;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getIsbn();
    }

    @Override
    public Long component4() {
        return getAuthorId();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component6() {
        return getUpdatedAt();
    }

    @Override
    public LocalDateTime component7() {
        return getDeletedAt();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getIsbn();
    }

    @Override
    public Long value4() {
        return getAuthorId();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value6() {
        return getUpdatedAt();
    }

    @Override
    public LocalDateTime value7() {
        return getDeletedAt();
    }

    @Override
    public BooksRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public BooksRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public BooksRecord value3(String value) {
        setIsbn(value);
        return this;
    }

    @Override
    public BooksRecord value4(Long value) {
        setAuthorId(value);
        return this;
    }

    @Override
    public BooksRecord value5(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public BooksRecord value6(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public BooksRecord value7(LocalDateTime value) {
        setDeletedAt(value);
        return this;
    }

    @Override
    public BooksRecord values(Long value1, String value2, String value3, Long value4, LocalDateTime value5, LocalDateTime value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BooksRecord
     */
    public BooksRecord() {
        super(Books.BOOKS);
    }

    /**
     * Create a detached, initialised BooksRecord
     */
    public BooksRecord(Long id, String title, String isbn, Long authorId, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(Books.BOOKS);

        setId(id);
        setTitle(title);
        setIsbn(isbn);
        setAuthorId(authorId);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        resetChangedOnNotNull();
    }
}
