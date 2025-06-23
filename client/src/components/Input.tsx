import { useState } from "react";

const Input = ({
  label,
  name,
  placeholder,
  onChange,
  className,
  focus,
}: {
  label?: string;
  name: string;
  placeholder: string;
  onChange?: () => void;
  className?: string;
  focus?: boolean;
}) => {
  const [isFocused, setIsFocused] = useState(focus? focus : false);

  return (
    <div  className={`min-w-[300px] px-4 py-2 rounded-2xl border-2 ${isFocused? " border-primary" : "border-gray-400"}  ${className? className : ""}`}>
      {label && <label htmlFor={name}>{label}</label>}
      <input
        type="text"
        className="w-full outline-none"
        name={name}
        placeholder={placeholder}
        onChange={onChange && onChange}
        onFocus={() => setIsFocused(true)}
        onBlur={() => setIsFocused(false)}
        autoFocus={focus}
      />
    </div>
  );
};

export default Input;
