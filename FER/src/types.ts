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

export type UserRoles = 'admin' | 'moderator' | 'user';