import React from "react";

export type SChildren = React.ReactNode;

export type NoteDto = {
    id: number,
    userId: string,
    value: string;
}

export type PostDto = {
    id: number,
    author: string,
    postBody: { bodyType: 'text' | 'image', bodyValue: string }[]
}