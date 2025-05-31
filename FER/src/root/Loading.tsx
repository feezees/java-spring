import { Layout } from "../ui/Layout"

export const Loading = () => {
    return (
        <Layout centred>
            <div className="w-[300px] h-[300px] overflow-hidden flex justify-center items-center">
                <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" className="w-full text-center animate-spin">
                    <style>{`
                        .spinner_V8p9{transform-origin:center;animation:spinner_zKoa .75s infinite linear}@keyframes spinner_zKoa{100%{transform:rotate(360deg)}}
                    `}</style>
                    <path d="M12,4V2A10,10 0 0,0 2,12H4A8,8 0 0,1 12,4Z" className="spinner_V8p9" fill="currentColor" />
                </svg>
            </div>
        </Layout>
    )
}