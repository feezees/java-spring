import React from "react";

export type SChildren = React.ReactNode;

export type NoteDto = {
    id: number,
    userId: string,
    value: string;
}

export interface PostBodyItem {
    bodyType: 'text' | 'image';
    bodyValue: string;
}

export interface PostDto {
    id: number;
    author: string;
    postBody: PostBodyItem[];
}

export interface UserDto { id: string, username: string };

export type UserRoles = 'admin' | 'moderator' | 'user';

export type Routes = 'toppings' | 'notes' | 'posts' | 'tenders' | 'users' | '';

export enum Category {
    FRUITS = 'FRUITS',
    LIQUIDS = 'LIQUIDS',
    ADDITIONALS = 'ADDITIONALS'
}

export interface Topping {
    id: number;
    name: string;
    price: number;
    quantity: number;
    category: Category;
}
